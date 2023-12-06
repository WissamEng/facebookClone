const handleSubmit = async function (event) {
	console.log(11);
	event.preventDefault();
	const password = document.querySelector("#password").value;
	const confirmPassword = document.querySelector("#confirmPassword").value;
	if (password !== confirmPassword) {
		document.querySelector("#error").textContent =
			"Please confirm your password";
		return;
	}
	const form = document.querySelector("#form");
	const formData = new FormData(form);
	let data = {};
	formData.forEach((value, key) => {
		data[key] = value;
	});

	try {
		const response = await axios({
			method: "post",
			url: "http://localhost:8090/user/register",
			data,
		});
		console.log(response);
	} catch (error) {
		console.log(error);
	}
};

document.querySelector("#submit").addEventListener("click", handleSubmit);
