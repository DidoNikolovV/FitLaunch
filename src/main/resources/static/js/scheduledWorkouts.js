const url = 'http://localhost:8080'


const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content

const calendar = document.getElementById('calendar');
const username = document.getElementById("username").value

// function scheduledWorkoutAsHTML(workout) {
//     let scheduledWorkoutHTML = `<div id="${workout.id}">\n`
//     scheduledWorkoutHTML += `<p>${workout.scheduledDateTime}</p>\n`
//     scheduledWorkoutHTML += '</div>\n'
//
//     return scheduledWorkoutHTML;
// }

function updateCalendarEvents() {
    // Fetch scheduled workouts from the backend
    fetch(`${url}/api/users/${username}/calendar/scheduledWorkouts`, {
        headers: {
            "Accept": "application/json"
        }
    }).then(res => res.json())
        .then(data => {
            if (data && data.length > 0) {
                // console.log(data);

                let calendarEvents = data.map(workout => ({
                    id: workout.id,
                    name: workout.clientName,
                    date: moment(workout.scheduledDateTime).format('MM-DD-YYYY'),
                    description: "Random desc",
                    type: "event"
                }));

                console.log(calendarEvents);


                // Use setCalendarEvents to update the calendar events
                $(calendar).evoCalendar({
                    onMonthChange: updateCalendarEvents,
                    onYearChange: updateCalendarEvents,
                    calendarEvents: calendarEvents
                });
            }else {
                console.log("No scheduled workouts found.");
            }
        })
        .catch(error => console.error('Error fetching data:', error));
}
updateCalendarEvents();





