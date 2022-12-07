//this page would only show books available
//keep track of what store the book is available

var authHeaderValue = null;
var username = null;
var password = null;

let empSect = document.getElementById("employee");
let bookResults = document.getElementById("bookArea");
let admSect = document.getElementById("adm_Section");

//this is the function for the login
function login() {
    username = document.getElementById('txtUsername').value;
    password = document.getElementById('txtPassword').value;
    authHeaderValue = "Basic " + btoa(username + ":" + password);
    var form = document.getElementById("emp_ReceiptForm");

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "/login");
    xmlHttp.setRequestHeader('Authorization', authHeaderValue);
    xmlHttp.onreadystatechange = function () {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            console.log("LOGIN SUCCESS");
            //stop showing login
            toggleLogin();
            form.style.display = "block";
            //show logout and welcome message
            //save info for the session
            let role = this.responseText
            userStorage(username, password, role);
            togglePage(role);
        } else if (this.readyState == XMLHttpRequest.DONE && this.status == 401) {
            document.getElementById("errorMsg").innerHTML = "Invalid Username or Password";
            //keep showing login
        }
    }
    xmlHttp.send();
    username = document.getElementById("txtUsername").value = "";
    password = document.getElementById("txtPassword").value = "";
}

//this is an example function that shows and hides elements
// function myFunction() {
//     var x = document.getElementById("myDIV");
//     if (x.style.display === "none") {
//         x.style.display = "block";
//     } else {
//         x.style.display = "none";
//     }
// }

function toggleLogin() {
    var loginForm = document.getElementById("login");
    var logoutBtn = document.getElementById("logout");
    var receiptForm = document.getElementById("emp_ReceiptForm");
    var bookArea = document.getElementById("bookArea");

    if (loginForm.style.display === "none") {
        //show login, when logging in :)
        loginForm.style.display = "block";
        logoutBtn.style.display = "none";
        var error = document.getElementById("catchError").value = "";
    } else {
        //hide login, when logging out :)
        loginForm.style.display = "none";
        logoutBtn.style.display = "inline-block";
        var search = document.getElementById("emp_Search").value = "";
        var ISBN = document.getElementById("emp_ISBN").value = "";
        var Price = document.getElementById("emp_Price").value = "";
        var error = document.getElementById("catchError").innerHTML = "";

    }
    togglePage(localStorage.getItem("role"))
}

//storage for username and password
//session based
function userStorage(strUsername, strPassword, strRole) {
    sessionStorage.setItem("username", strUsername);
    sessionStorage.setItem("password", strPassword);
    sessionStorage.setItem("role", strRole);
}

//addBook finally works
function addBook() {
    //tell them the book has been added :)
    var ISBN = document.getElementById("adm_ISBN").value;
    var title = document.getElementById("adm_title").value;
    var author = document.getElementById("adm_author").value;
    var publish = document.getElementById("adm_publishDate").value;
    var edition = document.getElementById("adm_edition").value;
    var price = document.getElementById("adm_price").value;
    var error = document.getElementById("adm_catchError");
    var object = {
        "ISBN": ISBN, //strings work fine
        "title": title, //string
        "author": author, //int works fine
        "publishDate": publish, //SQL date
        "edition": edition, //int
        "purchasePrice": price //double
    }
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", "http://localhost:8080/book/create");
    xmlHttp.setRequestHeader("Content-Type", "application/json"); //sends json
    xmlHttp.setRequestHeader("Authorization", authHeaderValue); //put auth header into request header
    xmlHttp.onreadystatechange = function () {
        console.log("Ready state " + this.status);
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            getAllBooks();
            error.innerHTML = "Book is ready to be sold"
        }
    }
    xmlHttp.send(JSON.stringify(object));
    var ISBN = document.getElementById("adm_ISBN").value = "";
    var title = document.getElementById("adm_title").value = "";
    var author = document.getElementById("adm_author").value = "";
    var publish = document.getElementById("adm_publishDate").value = "";
    var edition = document.getElementById("adm_edition").value = "";
    var price = document.getElementById("adm_price").value = "";
}

//all good
function getAllBooks() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            var objects = JSON.parse(this.responseText);
            renderBooks(objects);
        }
    }
    xmlHttp.open("GET", "http://localhost:8080/book/findAll", true);
    xmlHttp.send();
}

function getBookBYTitle() {
    var findTitle = document.getElementById("emp_Search").value;
    var error = document.getElementById("catchError");
    if (findTitle == "" || findTitle == " ") {
        error.innerHTML = "Title search field empty. Please add a title and try again";
    } else {
        error.innerHTML = "";
        bookResults.style.display = 'block';
        console.log(findTitle);
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
                var objects = JSON.parse(this.responseText);
                console.log(objects);
                renderBooks(objects);
            }
        }
        xmlHttp.open("GET", "http://localhost:8080/book/find/" + findTitle, true);
        xmlHttp.setRequestHeader("Content-Type", "application/json"); //sends json
        xmlHttp.setRequestHeader("Authorization", authHeaderValue); //put auth header into request header
        xmlHttp.send();
    }
}

function getBookByAuthor() {
    var findAuthor = document.getElementById("emp_Search").value;
    var error = document.getElementById("catchError");
    if (findAuthor == "" || findAuthor == " ") {
        error.innerHTML = "Author search field empty. PLease add a author and try again";
    } else {
        error.innerHTML = "";
        bookResults.style.display = 'block';
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
                var objects = JSON.parse(this.responseText);
                console.log(objects);
                renderBooks(objects);
            }
        }
        xmlHttp.open("GET", "http://localhost:8080/book/findAuthor/" + findAuthor, true);
        xmlHttp.setRequestHeader("Content-Type", "application/json"); //sends json
        xmlHttp.setRequestHeader("Authorization", authHeaderValue); //put auth header into request header
        xmlHttp.send();
    }

}

//as of now only grabs the first result
function grabValue(isbn, value) {
    document.getElementById("emp_ISBN").value = isbn;
    document.getElementById("emp_Price").value = value;
}

//works good
function renderBooks(books) {
    var bookList = document.getElementById("bookList");
    bookList.innerHTML = "";
    var bookHTML = ""
    bookHTML += "<table><tr>" +
        "<th>" + "ISBN" + "</th>" +
        "<th>" + "Title" + "</th>" +
        "<th>" + "Author ID" + "</th>" +
        "<th>" + "Author Name" + "</th>" +
        "<th>" + "Publish Date" + "</th>" +
        "<th>" + "Purchase Price" + "</th>" +
        "</tr> "

    for (var object of books) {
        bookHTML += "<tr onclick='grabValue(" + object.ISBN + ", " + object.purchasePrice + ")'>" +
            "<td id='grabISBN'>" + object.ISBN + "</td>" +
            "<td>" + object.title + "</td>" +
            "<td>" + object.author + "</td>" +
            "<td>" + object.authorName + "</td>" +
            "<td>" + object.publishDate + "</td>" +
            "<td id='grabPrice'>" + "$" + object.purchasePrice + "</td>"
        bookList.innerHTML = bookHTML;
    }
}

//clear page when moving in between pages
function sendBackReceipt() {
    var ISBN = document.getElementById("emp_ISBN").value;
    var price = document.getElementById("emp_Price").value;
    var error = document.getElementById("catchError");
    var object = {
        "bookISBN": ISBN,
        "salePrice": price
    }
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", "http://localhost:8080/receipts");
    xmlHttp.setRequestHeader("Content-Type", "application/json"); //sends json
    xmlHttp.setRequestHeader("Authorization", authHeaderValue); //put auth header into request header
    xmlHttp.onreadystatechange = function () {
        console.log("Ready state ", this.status);
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            error.innerHTML = "Thank you for your purchase";
            getAllBooks();
        }
    }
    xmlHttp.send(JSON.stringify(object));
    var ISBN = document.getElementById("emp_ISBN").value = "";
    var price = document.getElementById("emp_Price").value = "";
}

function togglePage(role) {
    if (role == "[ROLE_ADMIN]") {
        empSect.style.display = 'none';
        bookResults.style.display = 'none'; //to make this area appear when searching
        admSect.style.display = 'block';
    } else if (role == "[ROLE_EMP]") {
        empSect.style.display = 'block';
        bookResults.style.display = 'none';
        admSect.style.display = 'none'
    } else {
        empSect.style.display = 'none';
        bookResults.style.display = 'none';
        admSect.style.display = 'none';
    }
}


// all js from the admin page

let currentChart;

function getBookSales() {
    if(checkMonth()) {
        let month = document.getElementById("adm_displayMonth").value;
        if (month === "") {
            month = "0";
        }

        let xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET", "http://localhost:8080/receipts/books/" + month);
        xmlHttp.setRequestHeader("Authorization", authHeaderValue);
        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                let data = JSON.parse(this.responseText);

                let chartData = {
                    datasets: []
                }
                data.forEach(function (book) {
                    let r = Math.floor(Math.random() * 255);
                    let g = Math.floor(Math.random() * 255);
                    let b = Math.floor(Math.random() * 255);
                    let rgb = "rgb(" + r + "," + g + "," + b + ")";
                    chartData.datasets.push({
                        label: book.title,
                        data: [{
                            x: book.numberOfSales,
                            y: book.salesTotal
                        }],
                        backgroundColor: rgb
                    });
                });

                renderScatterData(chartData, "Books Sales");
            }
        }
        xmlHttp.send();
    }
}

function getStoreSales() {
    if(checkMonth()) {
        let month = document.getElementById("adm_displayMonth").value;
        if (month === "") {
            month = "0";
        }

        let xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET", "http://localhost:8080/receipts/stores/" + month);
        xmlHttp.setRequestHeader("Authorization", authHeaderValue);
        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                let data = JSON.parse(this.responseText);

                let chartData = {
                    datasets: []
                }
                data.forEach(function (store) {
                    let r = Math.floor(Math.random() * 255);
                    let g = Math.floor(Math.random() * 255);
                    let b = Math.floor(Math.random() * 255);
                    let rgb = "rgb(" + r + "," + g + "," + b + ")";
                    chartData.datasets.push({
                        label: store.storeName,
                        data: [{
                            x: store.numberOfSales,
                            y: store.salesTotal
                        }],
                        backgroundColor: rgb
                    });
                });
                renderScatterData(chartData, "Stores Sales")
            }
        }
        xmlHttp.send();
    }
}

function getAuthorSales() {
    if(checkMonth()) {
        let month = document.getElementById("adm_displayMonth").value;
        if (month === "") {
            month = "0";
        }

        let xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET", "http://localhost:8080/receipts/authors/" + month);
        xmlHttp.setRequestHeader("Authorization", authHeaderValue);
        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                let data = JSON.parse(this.responseText);

                let chartData = {
                    datasets: []
                }
                data.forEach(function (author) {
                    let r = Math.floor(Math.random() * 255);
                    let g = Math.floor(Math.random() * 255);
                    let b = Math.floor(Math.random() * 255);
                    let rgb = "rgb(" + r + "," + g + "," + b + ")";
                    chartData.datasets.push({
                        label: author.authorName,
                        data: [{
                            x: author.numberOfSales,
                            y: author.salesTotal
                        }],
                        backgroundColor: rgb
                    });
                });
                renderScatterData(chartData, "Author Sales")
            }
        }
        xmlHttp.send();
    }
}

function checkMonth() {
    let month = document.getElementById("adm_displayMonth").value;
    if((0 > month || month > 12) && month !== "") {
        console.log("Here")
        document.getElementById("adm_chartError").innerHTML = "Not a valid month. 0 for current month or 1-12";
        return false;
    } else {
        document.getElementById("adm_chartError").innerHTML = "";
        return true;
    }
}

function getSalesPerStoreEachMonth() {
    document.getElementById("adm_chartError").innerHTML = "";
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:8080/receipts/stores/each");
    xmlHttp.setRequestHeader("Authorization", authHeaderValue);
    xmlHttp.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            let data = JSON.parse(this.responseText);

            let chartData = {
                labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
                datasets: []
            }

            let lastStore = null;
            data.forEach(function (point) {
                if (lastStore !== point.storeName) {
                    lastStore = point.storeName;

                    let r = Math.floor(Math.random() * 255);
                    let g = Math.floor(Math.random() * 255);
                    let b = Math.floor(Math.random() * 255);
                    let rgb = "rgb(" + r + "," + g + "," + b + ")";
                    chartData.datasets.push({
                        label: point.storeName,
                        data: [point.salesTotal],
                        fill: false,
                        borderColor: rgb,
                        tension: 0.1
                    });
                } else {
                    chartData.datasets[chartData.datasets.length - 1].data.push(point.salesTotal);
                }
            });

            renderLine(chartData);
        }
    }
    xmlHttp.send();
}

// example data
// {
//     datasets: [{
//         label: "Book Title Here",
//         data: [{
//             x: 5,
//             y: 25.2
//         }, {
//             x: 10,
//             y: 50
//         }],
//         backgroundColor: 'rgb(255, 99, 132)'
//     }, {
//         label: "Book Title Here 2",
//         data: [{
//             x: 2,
//             y: 10.0
//         }, {
//             x: 15,
//             y: 67.7
//         }],
//         backgroundColor: 'rgb(99,115,255)'
//     }]
// }

function renderScatterData(data, type) {
    if (currentChart) {
        currentChart.destroy();
    }

    let graph = document.getElementById("adm_bookChart");

    currentChart = new Chart(graph, {
        type: 'scatter',
        data: data,
        options: {
            scales: {
                x: {
                    min: 0,
                    title: {
                        display: true,
                        text: "Number Of Sales"
                    }
                },
                y: {
                    title: {
                        display: true,
                        text: "Sales Total"
                    }
                }
            },
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    callbacks: {
                        label: function (data) {
                            return data.dataset.label + ": " + data.dataset.data[data.dataIndex].x + ", $" + data.dataset.data[data.dataIndex].y;
                        }
                    }
                }
            }
        }
    });
}

function renderLine(data) {
    if (currentChart) {
        currentChart.destroy();
    }

    let graph = document.getElementById("adm_bookChart");

    currentChart = new Chart(graph, {
        type: "line",
        data: data
    });
}

function hideShowAdmin(view) {
    let chartView = document.getElementById("adm_chartView");
    let userView = document.getElementById("adm_UserManaging");
    let bookView = document.getElementById("adm_Book");
    console.log(bookView)
    switch (view) {
        case "chart":
            chartView.style.display = "block";
            userView.style.display = "none";
            bookView.style.display = "none";
            break;
        case "user":
            chartView.style.display = "none";
            userView.style.display = "block";
            bookView.style.display = "none";
            break;
        case "book":
            console.log("Here")
            chartView.style.display = "none";
            userView.style.display = "none";
            bookView.style.display = "block";
            break;
    }
}

function adm_createUser() {
    let adm_newUser = document.getElementById("adm_newUsername").value;
    let adm_newPass = document.getElementById("adm_password").value;
    let adm_storeName = document.getElementById("adm_store").value;
    let adm_isAdmin = document.getElementById("adm_isAdmin").value;
    let role;
    if (adm_isAdmin) {
        role = "ADMIN"
    } else {
        role = "EMP"
    }
    let newUser = {
        "username": adm_newUser,
        "password": adm_newPass,
        "role": role,
        "fkStoreName": adm_storeName
    }
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", "http://localhost:8080/users")
    xmlHttp.setRequestHeader("Content-Type", "application/json")
    xmlHttp.setRequestHeader("Authorization", authHeaderValue)
    xmlHttp.onreadystatechange = function () {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            console.log("Successfully posted");
        }
    }
    xmlHttp.send(JSON.stringify(newUser));
}

//Create new users in admin page

window.onload = function () {
    getAllBooks();
    var form = document.getElementById("emp_ReceiptForm");
    form.style.display = "none";
    admSect.style.display = "none";
    empSect.style.display = "none";
}