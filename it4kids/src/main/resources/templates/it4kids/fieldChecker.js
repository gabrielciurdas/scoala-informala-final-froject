
function checkLogin() {
	var email = document.getElementsByName('email')[0].value;
	var password = document.getElementsByName('password')[0].value;
	if (email == "" || password == "") {
		alert("Trebuie să introduceți adresa de email și parola!");
		return false;
	}
	return true;
}

function checkUserRegistration() {
	var firstName = document.getElementsByName('firstName')[0].value;
	var lastName = document.getElementsByName('lastName')[0].value;
	var username = document.getElementsByName('userName')[0].value;
	var email = document.getElementsByName('email')[0].value;
	var password = document.getElementsByName('password')[0].value;

	if (firstName == "" || lastName == "" || email == "" || userName == ""
			|| password == "") {
		alert("Trebuie să introduceți datele în fiecare câmp!");
		return false;
	}

	if (password.length < 6) {
		alert("Parola trebuie să fie compusă din cel puțin 6 caractere");
		return false;
	}

	var re = /^\w+$/;
	if (!re.test(firstName) || !re.test(lastName) || !re.test(username)
			|| !re.test(password)) {
		alert("Nu poți introduce caractere speciale, decât litere sau cifre");
		return false;
	}

	return true;
}