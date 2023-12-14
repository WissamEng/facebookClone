const handleShareButton = async () => {
	const postContent = document.querySelector("#idea").value;
	if (postContent.trim() === "") {
		// document.querySelector(".alert").style.display = "block";
		// document.querySelector("#error").textContent =
		// 	"Please confirm your password";
		console.log("blank");
	} else {
		try {
			const res = await axios({
				method: "post",
				url: "http://localhost:8090/post/addNewPost",
				data: postContent,
				headers: {
					"content-Type": "text/plain",
				},
			});
			window.location.href = "http://localhost:8090/home";
		} catch (error) {
			// document.querySelector(".alert").style.display = "block";
			// document.querySelector("#error").innerHTML = error.response.data.message;
			console.log(error);
		}
	}
};

const handleEdit = (e) => {
	const postId = e.target.getAttribute("data-post-id");
	console.log(postId);

	// Navigate to the edit page
	window.location.href = `http://localhost:8090/post/edit?postId=${postId}`;
};

const handleDelete = (e) => {
	const isConfirmed = window.confirm("Are you sure to delete this post?");
	if (isConfirmed) {
		const postId = e.target.getAttribute("data-post-id");
		console.log(postId);

		// Navigate to the edit page
		window.location.href = `http://localhost:8090/post/delete?postId=${postId}`;
	}
};

// document
// 	.querySelector(".shareIdea")
// 	.addEventListener("click", handleShareButton);
const editBtn = document.querySelectorAll(".edit");
editBtn.forEach((item) => item.addEventListener("click", handleEdit));

const deleteBtn = document.querySelectorAll(".delete");
deleteBtn.forEach((item) => item.addEventListener("click", handleDelete));
