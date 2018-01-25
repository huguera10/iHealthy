/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


Parse.initialize("TQhl7DbLKccsTnRgMR6rtlCSWgo7KTdfGfGDlx32", "q6yDi6SBuC3LhL8ohXrCLFEFvdOdZY6kK8sMX3KL");
Parse.serverURL = "https://parseapi.back4app.com/";

var currentUser = Parse.User.current();
if (currentUser) {
    // do stuff with the user
    window.location = "newhtml.html";
}

function login() {
    Parse.User.logIn(document.getElementById("login_username").value, document.getElementById("login_password").value, {
        success: function (user) {
            // Do stuff after successful login.
            window.location = "newhtml.html";
        },
        error: function (user, error) {
            // The login failed. Check error to see why.
            alert("Error: " + error.message);
        }
    });
}

function sign_up() {
    var user = new Parse.User();
    user.set("username", document.getElementById("signup_username").value);
    user.set("password", document.getElementById("signup_password").value);
    user.set("email", document.getElementById("signup_email").value);
    user.signUp(null, {
        success: function (response) {
            window.location = "newhtml.html";
        },
        error: function (response, error) {
            alert("Erro: " + error.message);
        }
    });
}