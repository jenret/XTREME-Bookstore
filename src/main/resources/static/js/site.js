//this page would only show books available
//ISBN will ID
//keep track of what store the book is available
//so when an employee logs in, make visible the receipt form
//if admin, show how well the stores are doing, show sales etc.
//render the login first, and then ^

var authHeaderValue = null;
var username = null;
var password = null;

//this is the function for the login
function login() {
    username = document.getElementById('txtUsername').value;
    password = document.getElementById('txtPassword').value;
    authHeaderValue = "Basic " + btoa(username + ":" + password);
    var form = document.getElementById("receiptForm");

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
            userStorage(username, password);
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
    var receiptForm = document.getElementById("receiptForm");
    var bookArea = document.getElementById("bookArea");
    var graphArea = document.getElementById("graphArea");

    if (loginForm.style.display === "none") {
        //show login
        loginForm.style.display = "block";
        logoutBtn.style.display = "none";
        receiptForm.style.display = "none";
        bookArea.style.display = "none";
        graphArea.style.display = "none";
    } else {
        //hide login
        loginForm.style.display = "none";
        logoutBtn.style.display = "inline-block";
        receiptForm.style.display = "block";
        bookArea.style.display = "block";
        graphArea.style.display = "block";
    }
}

//storage for username and password
//session based
function userStorage(strUsername, strPassword) {
    sessionStorage.setItem("username", strUsername);
    sessionStorage.setItem("password", strPassword);
}

//addBook finally works
function addBook() {
    var ISBN = document.getElementById("ISBN").value;
    var title = document.getElementById("title").value;
    var author = document.getElementById("author").value;
    var publish = document.getElementById("publishDate").value;
    var edition = document.getElementById("edition").value;
    var price = document.getElementById("price").value;
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
        console.log("Ready state "+ this.status);
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            console.log("Successfully posted");
            getAllBooks();
        }
    }
    xmlHttp.send(JSON.stringify(object));
    var ISBN = document.getElementById("ISBN").value = "";
    var title = document.getElementById("title").value = "";
    var author = document.getElementById("author").value = "";
    var publish = document.getElementById("publishDate").value = "";
    var edition = document.getElementById("edition").value = "";
    var price = document.getElementById("price").value = "";
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

//works good
function renderBooks(books) {
    var bookList = document.getElementById("bookList");
    bookList.innerHTML = "";
    var bookHTML = ""
    bookHTML += "<table><tr>" +
        "<th>" + "ISBN" + "</th>" +
        "<th>" + "Title" + "</th>" +
        "<th>" + "Author ID" + "</th>" +
        "<th>" + "Edition" + "</th>" +
        "<th>" + "Publish Date" + "</th>" +
        "<th>" + "Purchase Price" + "</th>" +
        "</tr> "
    for (var object of books) {
        bookHTML += "<tr>" +
            "<td>" + object.ISBN + "</td>" +
            "<td>" + object.title + "</td>" +
            "<td>" + object.author + "</td>" +
            "<td>" + object.edition + "</td>" +
            "<td>" + object.publishDate + "</td>" +
            "<td>" + "$" + object.purchasePrice + "</td>"
        bookList.innerHTML = bookHTML;
    }
}

function sendBackReceipt() {
    var ISBN = document.getElementById("ISBN").value;
    var price = document.getElementById("price").value;
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
            console.log("Successfully posted");
            getAllBooks();
        }
    }
    xmlHttp.send(JSON.stringify(object));
    var ISBN = document.getElementById("ISBN").value = "";
    var price = document.getElementById("price").value = "";
}


window.onload = function () {
    var form = document.getElementById("receiptForm");
    form.style.display = "none";
    getAllBooks();
}