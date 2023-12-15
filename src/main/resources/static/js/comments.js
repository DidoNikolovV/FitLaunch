const url = 'http://localhost:8080'

const workoutId = document.getElementById('workoutId').value
const weekId = document.getElementById('weekId').value
const programId = document.getElementById('programId').value
const commentForm = document.getElementById('commentForm')
commentForm.addEventListener('submit', postComment)

const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content

const commentContainer = document.getElementById('commentCtnr');
const showCommentsBtn = document.getElementById('showComments');




async function postComment(e) {
    e.preventDefault();

    const messageValue = document.getElementById('message').value;

    fetch(`${url}/api/${programId}/${weekId}/${workoutId}/comments`, {
        method: 'POST',
        headers: {
         'Content-Type': 'application/json',
         'Accept': 'application/json',
         [csrfHeaderName]: csrfHeaderValue
        },
        body: JSON.stringify({
            message: messageValue
        })
    }).then(res => res.json())
        .then(data => {
            document.getElementById('message').value = ""
            commentContainer.innerHTML += commentAsHTML(data)
        })
}

function commentAsHTML(comment) {
    let commentHTML = '<div>\n'
    commentHTML += `<h4>${comment.authorName}</h4>\n`
    commentHTML += `<p>${comment.message}</p>\n`
    commentHTML += `<button class="btn btn-danger" onClick="deleteComment(${comment.id})">Delete</button>\n`
    commentHTML += '</div>\n'

    return commentHTML;
}

async function deleteComment(commentId) {
    fetch(`${url}/api/${workoutId}/comments/${commentId}`, {
        method: 'DELETE',
        headers: {
            [csrfHeaderName]: csrfHeaderValue
        }
    }).then(res => res.json())
        .then(data => {
            commentContainer.removeChild(data)
        })
}

fetch(`${url}/api/${programId}/${weekId}/${workoutId}/comments`, {
    headers: {
        "Accept": "application/json"
    }
}).then(res => res.json())
    .then(data => {
        for(let comment of data) {
            commentContainer.innerHTML += commentAsHTML(comment)
        }
    })