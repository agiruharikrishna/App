document.addEventListener("DOMContentLoaded", function () {
    console.log("Student Attendance App Loaded!");

    const studentForm = document.getElementById("studentForm");
    const studentNameInput = document.getElementById("studentName");

    if (studentForm) {
        studentForm.addEventListener("submit", async function (event) {
            event.preventDefault();
            let studentName = studentNameInput.value.trim();

            if (studentName === "") {
                alert("Student name cannot be empty!");
                return;
            }

            // Disable the button to prevent duplicate submissions
            const submitButton = studentForm.querySelector("button");
            submitButton.disabled = true;

            try {
                let response = await fetch("/addStudent", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ name: studentName }),
                });

                if (!response.ok) throw new Error("Failed to add student");

                location.reload(); // Reload to update student list
            } catch (error) {
                console.error("Error adding student:", error);
                alert("❌ Error: Unable to add student");
            } finally {
                submitButton.disabled = false; // Re-enable the button
            }
        });
    }
});

async function toggleAttendance(studentId) {
    try {
        let response = await fetch(`/updateAttendance/${studentId}`, { method: "PUT" });

        if (!response.ok) throw new Error("Failed to update attendance");

        let statusElement = document.getElementById(`attendance-status-${studentId}`);
        if (statusElement) {
            statusElement.textContent = statusElement.textContent === "Present" ? "Absent" : "Present";

            // Smooth transition effect
            statusElement.style.transition = "background 0.3s ease-in-out";
            statusElement.style.backgroundColor = "#d4edda"; // Light green
            setTimeout(() => statusElement.style.backgroundColor = "", 1000);
        }
    } catch (error) {
        console.error("Error updating attendance:", error);
        alert("❌ Error: Unable to update attendance");
    }
}
