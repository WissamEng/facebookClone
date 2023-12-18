const handleShareButton = async () => {
	const postContent = document.querySelector("#idea").value;
	if (postContent.trim() === "") {
		console.log("blank");
	} else {
		try {
			const res = await axios({
				method: "post",
				url: `http://${serverUrl}/post/addNewPost`,
				data: postContent,
				headers: {
					"content-Type": "text/plain",
				},
			});
			window.location.href = `http://${serverUrl}/home`;
		} catch (error) {
			console.log(error);
		}
	}
};

const handleEdit = (e) => {
	const postId = e.target.getAttribute("data-post-id");
	console.log(postId);

	// Navigate to the edit page
	window.location.href = `http://${serverUrl}/post/edit?postId=${postId}`;
};

const handleDelete = (e) => {
	const isConfirmed = window.confirm("Are you sure to delete this post?");
	if (isConfirmed) {
		const postId = e.target.getAttribute("data-post-id");
		console.log(postId);

		// Navigate to the edit page
		window.location.href = `http://${serverUrl}/post/delete?postId=${postId}`;
	}
};

const handleEditComment = (e) => {
	console.log(11);
	const commentId = e.target.getAttribute("data-comment-id");
	console.log(commentId);

	// Navigate to the edit page
	window.location.href = `http://${serverUrl}/comment/edit?commentId=${commentId}`;
};

const handleCommentDelete = (e) => {
	const isConfirmed = window.confirm("Are you sure to delete this comment?");
	if (isConfirmed) {
		const commentId = e.target.getAttribute("data-comment-id");
		console.log(commentId);

		window.location.href = `http://${serverUrl}/comment/delete?commentId=${commentId}`;
	}
};

const editBtn = document.querySelectorAll(".edit");
editBtn.forEach((item) => item.addEventListener("click", handleEdit));

const deleteBtn = document.querySelectorAll(".delete");
deleteBtn.forEach((item) => item.addEventListener("click", handleDelete));

const editCommentBtn = document.querySelectorAll(".commentEdit");
editCommentBtn.forEach((item) =>
	item.addEventListener("click", handleEditComment)
);

const deleteCommentBtn = document.querySelectorAll(".commentDelete");
deleteCommentBtn.forEach((item) =>
	item.addEventListener("click", handleCommentDelete)
);
