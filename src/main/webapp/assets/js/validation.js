document.addEventListener("DOMContentLoaded", function() {
    // for add course page
    var courseNameInput = document.getElementById("courseNameInput");
    var courseSubmitButton = document.getElementById("courseSubmitButton");
    var courseNameError = document.getElementById("courseNameError");
    if(courseNameInput && courseNameError) {
        courseNameInput.addEventListener("input", function() {
            var inputValue = this.value.trim();
            console.log(inputValue)
            if (inputValue.length >= 1 && inputValue.length <= 20) {
                console.log("hi")
                courseNameError.textContent = "";
                courseSubmitButton.disabled = false;
            } else {
                courseNameError.textContent = "Name must be between 1 and 20 characters";
                courseSubmitButton.disabled = true;
            }
        });
        courseSubmitButton.addEventListener("click", function(event) {
            event.preventDefault(); // Prevent default form submission
            
            Swal.fire({
                icon: 'success',
                title: 'Course Created Successfully',
                showConfirmButton: true,
            }).then(function() {
                document.querySelector('.addCourseForm').submit();
            });
        });
    }

    // for add student page
    var studentNameInput = document.getElementById("studentNameInput");
    var studentNameError = document.getElementById("studentNameError");

    var studentSubmitButton = document.getElementById("studentSubmitButton");
    if(studentNameInput) {
        studentNameInput.addEventListener("input", function() {
            var inputValue = this.value.trim();
            console.log(inputValue)
            if (inputValue.length >= 1 && inputValue.length <= 20) {
                studentNameError.textContent = "";
            } else {
                studentNameError.textContent = "Name must be between 1 and 20 characters";
            }
        });
    }

    var studentPhoneInput = document.getElementById("studentPhoneInput");
    var studentPhoneError = document.getElementById("studentPhoneError");
    if(studentPhoneInput) {
        studentPhoneInput.addEventListener("input", function() {
            var inputValue = this.value.trim();
            console.log(inputValue)
            if (inputValue.length >= 1 && inputValue.length <= 11) {
                studentPhoneError.textContent = "";
            } else {
                studentPhoneError.textContent = "Phone number must be between 1 and 11 characters";
            }
        });
    }

    var studentEmailInput = document.getElementById("studentEmailInput");
    var studentEmailError = document.getElementById("studentEmailError");
    if(studentEmailInput) {
        studentEmailInput.addEventListener("input", function() {
            var inputValue = this.value.trim();
            console.log(inputValue)
            if (inputValue.length >= 1) {
                studentEmailError.textContent = "";
            } else {
                studentEmailError.textContent = "Email is required";
            }
        });
    }

    var currentDate = new Date();    
    var studentDOBInput = document.getElementById("studentDOBInput");
    var studentDOBError = document.getElementById("studentDOBError");
    if(studentDOBInput) {
        studentDOBInput.addEventListener("input", function() {
            var inputValue = this.value;
            var dob = new Date(inputValue);
            var age = currentDate.getFullYear() - dob.getFullYear();
            var monthDiff = currentDate.getMonth() - dob.getMonth();

            if (monthDiff < 0 || (monthDiff === 0 && currentDate.getDate() < dob.getDate())) {
                age--;
            }

            if (age >= 13) {
                studentDOBError.textContent = "";
            } else {
                studentDOBError.textContent = "You must be at least 13 years old";
            }
        });
    }

    var studentEducationInput = document.getElementById("studentEducationInput");    
    var studentEducationError = document.getElementById("studentEducationError");
    if(studentEducationInput) {
        studentEducationInput.addEventListener("input", function() {
            var inputValue = this.value.trim();
            console.log(inputValue)
            if (inputValue.length >= 1 && inputValue.length <= 200) {
                studentEducationError.textContent = "";
            } else {
                studentEducationError.textContent = "Education must be between 1 and 200 characters";
            }
        });
    }

    // Handling photo input
    var photoInput = document.getElementById("photoImageInput");
    var photoError = document.getElementById("photoError");
    if(photoInput) {
        photoInput.addEventListener("change", function() {
            var file = this.files[0];
            var fileSize = file.size; // File size in bytes

            // Check if file size exceeds 5 MB (5 * 1024 * 1024 bytes)
            if(fileSize > (5 * 1024 * 1024)) {
                photoError.textContent = "File size cannot exceed 5 MB";
            } else {
                photoError.textContent = "";
            }
        });
    }
    
    // for forgot password page
    var forgotPasswordEmailInput = document.getElementById("forgotPasswordEmailInput");
    var forgotPasswordEmailSubmitButton = document.getElementById("forgotPasswordEmailSubmitButton");
    var forgotPasswordEmailError = document.getElementById("forgotPasswordEmailError");
    if(forgotPasswordEmailInput && forgotPasswordEmailError) {
        forgotPasswordEmailInput.addEventListener("input", function() {
            var inputValue = this.value.trim();
            console.log(inputValue)
            if (inputValue.length >= 1) {
                console.log("hi")
                forgotPasswordEmailError.textContent = "";
                forgotPasswordEmailSubmitButton.disabled = false;
            } else {
                forgotPasswordEmailError.textContent = "Email is required";
                forgotPasswordEmailSubmitButton.disabled = true;
            }
        });
    }
});
