//this page would only show books available
//ISBN will ID
//keep track of what store the book is available
//so when an employee logs in, make visible the receipt form
//if admin, show how well the stores are doing, show sales etc.
//render the login first, and then ^

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
            userStorage(username, password,role);
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
        //show login
        loginForm.style.display = "block";
        logoutBtn.style.display = "none";
    } else {
        //hide login
        loginForm.style.display = "none";
        logoutBtn.style.display = "inline-block";
    }
    togglePage(localStorage.getItem("role"))
}

//storage for username and password
//session based
function userStorage(strUsername, strPassword, strRole) {
    sessionStorage.setItem("username", strUsername);
    sessionStorage.setItem("password", strPassword);
    sessionStorage.setItem("role",strRole);
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
        console.log("Ready state " + this.status);
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

function getBookBYTitle(){
    var findTitle = document.getElementById("emp_Search").value;
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

//as of now only grabs the first result
function grabValue(){
    var grabISBN = document.getElementById("grabISBN").innerHTML;
    var grabPrice = document.getElementById("grabPrice").innerHTML;
    var salesISBN = document.getElementById("emp_ISBN").value = grabISBN;
    var salesPrice = document.getElementById("emp_Price").value = grabPrice;
    console.log(grabISBN);
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
        bookHTML += "<tr>" +
            "<td onclick=' grabValue();' id='grabISBN'>" + object.ISBN + "</td>" +
            "<td>" + object.title + "</td>" +
            "<td>" + object.author + "</td>" +
            "<td>" + object.authorName + "</td>" +
            "<td>" + object.publishDate + "</td>" +
            "<td onclick='grabValue();' id='grabPrice'>" + "$" + object.purchasePrice + "</td>"
        bookList.innerHTML = bookHTML;
    }
}

function sendBackReceipt() {
    var ISBN = document.getElementById("emp_ISBN").value;
    var price = document.getElementById("emp_Price").value;
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
    var ISBN = document.getElementById("emp_ISBN").value = "";
    var price = document.getElementById("emp_Price").value = "";
}
function togglePage(role){
    if(role =="[ROLE_ADMIN]"){
        empSect.style.display = 'none';
        bookResults.style.display = 'none'; //to make this area appear when searching
        admSect.style.display = 'block';
    }else if(role =="[ROLE_EMP]"){
        empSect.style.display = 'block';
        bookResults.style.display = 'none';
        admSect.style.display = 'none'
    }else{
        empSect.style.display = 'none';
        bookResults.style.display = 'none';
        admSect.style.display = 'none';
    }
}
//Create new users in admin page

window.onload = function () {
    getAllBooks();
    var form = document.getElementById("emp_ReceiptForm");
    form.style.display = "none";
    admSect.style.display = "none";
    empSect.style.display = "none";
}

//ben have error with this :( only commented out for sake of clairty
//chart javascript
// const ctx1 = document.getElementById('bookChart').getContext('2d');
// const ctx2 = document.getElementById('storeChart').getContext('2d');
// const ctx3 = document.getElementById('authorChart').getContext('2d');
// const ctx4 = document.getElementById('monthChart').getContext('2d');