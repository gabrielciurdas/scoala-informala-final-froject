function checkFields() {
	var userName = document.getElementsByName('userName')[0].value;
	var password = document.getElementsByName('password')[0].value;

	if (userName == "" || password == "" ) {
		alert("Trebuie să introduceți numele de utilizator și parola!");
		return false;
	}
	return true;
}