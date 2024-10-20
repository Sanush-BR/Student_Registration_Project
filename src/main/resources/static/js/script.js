

document.getElementById('userForm').addEventListener('submit', async function(event) {
       event.preventDefault(); // Prevent default form submission

       // Collect form data
       const formData = new FormData(this);
       const data = Object.fromEntries(formData.entries());

       try {
            // Send POST request using fetch
            const response = await fetch('http://52.66.146.180:8080/api/students', {
                			method: 'POST',
                   			 headers: {
                        		'Content-Type': 'application/json'
                    		},
                    		body: JSON.stringify(data) // Convert data to JSON
                		});

                // Check if the request was successful
                if (response.ok) {
                    //on success
                	alert("Student data saved");
                	//clear form fields
                	this.reset();
                } else {
					alert("Something went wrong");
                    throw new Error('Network response was not ok.');
                }
            } catch (error) {
                alert("System failure");
            }
        });
