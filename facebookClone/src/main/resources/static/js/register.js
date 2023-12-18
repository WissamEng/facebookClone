	console.log(serverUrl);

const handleSubmit = async function (event) {
	console.log(11);
	event.preventDefault();

	const password = document.querySelector("#password").value;
	const confirmPassword = document.querySelector("#confirmPassword").value;
	if (password !== confirmPassword) {
		document.querySelector(".alert").style.display = "block";
		document.querySelector("#error").textContent =
			"Please confirm your password";
		return;
	}
	const username = document.querySelector("#username").value;
	const email = document.querySelector("#email").value;

	// Create a JSON object from the form inputs
	let data = {
		password,
		username,
		email,
		// Add other form fields here as needed
	};

	try {
		const response = await axios({
			method: "post",
			url: `http://${serverUrl}/user/register`,
			data,
			headers: {
				"Content-Type": "application/json", // Ensure the content type is set to application/json
			},
		});
		console.log(response);
		if (response.status === 200) {
			window.location.href = `http://${serverUrl}/login_page?message=true`;
		}
	} catch (error) {
		console.log("error");
		document.querySelector(".alert").style.display = "block";
		document.querySelector("#error").innerHTML = error.response.data.message;
		console.log(error);
	}
};

document.querySelector("#submit").addEventListener("click", handleSubmit);
