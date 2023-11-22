const workoutId = document.getElementById('workoutId').value
const commentForm = document.getElementById('commentForm')
commentForm.addEventListener('submit', postComment)

const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content

const commentContainer = document.getElementById('commentCtnr');


async function postComment(e) {
    e.preventDefault();

    const messageValue = document.getElementById('message').value;

    fetch(`http://localhost:8080/${workoutId}/comments`, {
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
                commentContainer.innerHTML = commentAsHTML(data)
            })
}

function commentAsHTML(comment) {
    let commentHTML = '<div>\n'
    commentHTML += `<h4>${comment.authorName}</h4>\n`
    commentHTML += `<p>${comment.content}</p>\n`
    commentHTML += '</div>\n'

    return commentHTML;
}

function loadComments(e) {
    e.preventDefault();

    const messageVal = document.getElementById('message').value

    fetch(`http://localhost:8080/api/${workoutId}/comments`, {
        headers: {
            "Accept": "application/json"
        }
    }).then(res => res.json())
        .then(data => {
            for(let comment of data) {
                commentContainer.innerHTML += commentAsHTML(comment);
            }
        })
}