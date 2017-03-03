function checkLogin() {
	var userName = document.getElementsByName('userName')[0].value;
	var password = document.getElementsByName('password')[0].value;

	if (userName == "" || password == "" ) {
		alert("Trebuie să introduceți numele de utilizator și parola!");
		return false;
	}
	return true;
}

function checkUserRegistration() {
	var firstName = document.getElementsByName('firstName')[0].value;
	var lastName = document.getElementsByName('firstName')[0].value;
	var email = document.getElementsByName('email')[0].value;
	var username = document.getElementsByName('userName')[0].value;
	var password = document.getElementsByName('password')[0].value;

	if (firstName == "" || lastName == "" || email == "" 
		|| userName == "" || password == "" ) {
		alert("Trebuie să introduceți datele în fiecare câmp!");
		return false;
	}
	return true;
	
}