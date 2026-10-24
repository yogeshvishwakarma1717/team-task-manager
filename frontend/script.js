const API = "http://localhost:8080/api";

const todoColumn = document.getElementById("todoColumn");
const progressColumn = document.getElementById("progressColumn");
const doneColumn = document.getElementById("doneColumn");

const statusFilter = document.getElementById("statusFilter");
const memberFilter = document.getElementById("memberFilter");

const addTaskBtn = document.getElementById("addTaskBtn");
const taskModal = document.getElementById("taskModal");
const closeModal = document.getElementById("closeModal");
const taskForm = document.getElementById("taskForm");
const assigneeSelect = document.getElementById("assignee");


// =========================
// LOAD TASKS
// =========================

async function loadTasks() {

    const response = await fetch(`${API}/tasks`);
    const tasks = await response.json();

    todoColumn.innerHTML = "";
    progressColumn.innerHTML = "";
    doneColumn.innerHTML = "";

    tasks.forEach(task => {

        // Status filter
        if (
            statusFilter.value !== "ALL" &&
            task.status !== statusFilter.value
        ) {
            return;
        }

        // Member filter
        if (memberFilter.value !== "ALL") {

            if (
                !task.assignee ||
                task.assignee.id != memberFilter.value
            ) {
                return;
            }
        }

        const card = createTaskCard(task);

        if (task.status === "TODO") {
            todoColumn.appendChild(card);
        }

        else if (task.status === "IN_PROGRESS") {
            progressColumn.appendChild(card);
        }

        else if (task.status === "DONE") {
            doneColumn.appendChild(card);
        }
    });
}


// =========================
// CREATE TASK CARD
// =========================

function createTaskCard(task) {

    const card = document.createElement("div");

    card.className = "task-card";

    // Fast overdue detection
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    let overdue = false;

    if (task.dueDate) {

        const dueDate = new Date(task.dueDate);
        dueDate.setHours(0, 0, 0, 0);

        if (
            dueDate < today &&
            task.status !== "DONE"
        ) {
            overdue = true;
        }
    }

    if (overdue) {
        card.classList.add("overdue");
    }

    const assigneeName =
        task.assignee
            ? task.assignee.name
            : "Unassigned";

    card.innerHTML = `
        <h3>${task.title}</h3>

        <p>${task.description || ""}</p>

        <p>
            <strong>Assigned:</strong>
            ${assigneeName}
        </p>

        <p>
            <strong>Due:</strong>
            ${task.dueDate || "No date"}
        </p>

        <p>
            <strong>Priority:</strong>
            ${task.priority || "MEDIUM"}
        </p>

        ${
            overdue
            ? `<p class="overdue-text">⚠ OVERDUE</p>`
            : ""
        }

        <div class="task-actions">

            ${
                task.status !== "TODO"
                ? `<button onclick="changeStatus(${task.id}, 'TODO')">
                    To Do
                   </button>`
                : ""
            }

            ${
                task.status !== "IN_PROGRESS"
                ? `<button onclick="changeStatus(${task.id}, 'IN_PROGRESS')">
                    In Progress
                   </button>`
                : ""
            }

            ${
                task.status !== "DONE"
                ? `<button onclick="changeStatus(${task.id}, 'DONE')">
                    Done
                   </button>`
                : ""
            }

            <button onclick="deleteTask(${task.id})">
                Delete
            </button>

        </div>
    `;

    return card;
}


// =========================
// CHANGE STATUS
// =========================

async function changeStatus(id, status) {

    await fetch(
        `${API}/tasks/${id}/status?status=${status}`,
        {
            method: "PATCH"
        }
    );

    loadTasks();
}


// =========================
// DELETE TASK
// =========================

async function deleteTask(id) {

    await fetch(
        `${API}/tasks/${id}`,
        {
            method: "DELETE"
        }
    );

    loadTasks();
}


// =========================
// LOAD MEMBERS
// =========================

async function loadMembers() {

    const response = await fetch(`${API}/members`);
    const members = await response.json();

    memberFilter.innerHTML =
        `<option value="ALL">All Members</option>`;

    assigneeSelect.innerHTML =
        `<option value="">Unassigned</option>`;

    members.forEach(member => {

        memberFilter.innerHTML += `
            <option value="${member.id}">
                ${member.name}
            </option>
        `;

        assigneeSelect.innerHTML += `
            <option value="${member.id}">
                ${member.name}
            </option>
        `;
    });
}


// =========================
// ADD TASK MODAL
// =========================

addTaskBtn.addEventListener("click", () => {
    taskModal.style.display = "block";
});


closeModal.addEventListener("click", () => {
    taskModal.style.display = "none";
});


// =========================
// CREATE TASK
// =========================

taskForm.addEventListener("submit", async function(event) {

    event.preventDefault();

    const task = {

        title: document.getElementById("title").value,

        description:
            document.getElementById("description").value,

        dueDate:
            document.getElementById("dueDate").value,

        priority:
            document.getElementById("priority").value,

        status: "TODO",

        assignee:
            assigneeSelect.value
                ? {
                    id: Number(assigneeSelect.value)
                  }
                : null
    };

    await fetch(
        `${API}/tasks`,
        {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(task)
        }
    );

    taskForm.reset();

    taskModal.classList.remove("show");

    loadTasks();
});


// =========================
// FILTERS
// =========================

statusFilter.addEventListener(
    "change",
    loadTasks
);

memberFilter.addEventListener(
    "change",
    loadTasks
);


// =========================
// START APPLICATION
// =========================

loadMembers();
loadTasks();