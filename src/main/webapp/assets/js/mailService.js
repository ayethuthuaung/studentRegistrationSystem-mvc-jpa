/**
 * 
 */
document.addEventListener("DOMContentLoaded", function() {
function generateRandomPassword(length) {
    const charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+";
    let password = "";
    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * charset.length);
        password += charset[randomIndex];
    }
    return password;
    }
function sendMail() {	
	(function() {
		emailjs.init("WsE2hFeJ5wtOCXh6w"); // account public key
	})();
	let password = generateRandomPassword(6);
    const passwordInput = document.getElementById("password")
    passwordInput.value = password;
    console.log(password)
	var params = {
        sendername: `ACE Inspiration`,
        to: document.querySelector("#to").value,
        subject: `Welcome to ACE Inspiration`,
        replyto: `example@gmail.com`,
        message: `ACE Inspiration has been added to your account.
                    Your Password is : ${password}`,

    };
	
	console.log(params);

	var serviceID = "service_hp6qahr" // email service id
	var templateID = "template_ey1j7r8" // email template id
	emailjs.send(serviceID, templateID, params)
		.then(res => {
			alert("Email Sent Successfully!")
		})
		.catch();
}
});