const secondHand = document.querySelector(".second-hand");
const minHand = document.querySelector(".min-hand");
const hourHand = document.querySelector(".hour-hand");
function setDate() {
  const now = new Date();
  const seconds = now.getSeconds();
  const mins = now.getMinutes();
  const hours = now.getHours();
  const secondsDegress = (seconds / 60) * 360;
  const minsDegress = (mins / 60) * 360;
  const hoursDegress = (hours / 12) * 360;
  secondHand.style.transform = `rotate(${secondsDegress}deg)`;
  minHand.style.transform = `rotate(${minsDegress}deg)`;
  hourHand.style.transform = `rotate(${hoursDegress}deg)`;
}
setDate();
setInterval(setDate, 1000);

let noteHolder = document.getElementById("noteHolder");

function addNote(noteMessage) {
  let note = document.createElement("div");
  note.setAttribute("class", "d-flex align-items-center border-bottom py-2");
  note.setAttribute("id", `${noteMessage}`);
  let noteDiv = document.createElement("div");
  noteDiv.setAttribute("class", "w-100 ms-3");
  let noteAndBtn = document.createElement("div");
  noteAndBtn.setAttribute(
    "class",
    "d-flex w-100 align-items-center justify-content-between"
  );
  let noteSpan = document.createElement("span");
  noteSpan.setAttribute("class", "theNote");
  noteSpan.textContent = noteMessage;
  noteAndBtn.append(noteSpan);
  let deleteNoteBtn = document.createElement("button");
  deleteNoteBtn.setAttribute("class", "btn btn-sm");
  let deleteNoteBtnIcon = document.createElement("i");
  deleteNoteBtnIcon.setAttribute("class", "fa fa-times");
  deleteNoteBtnIcon.setAttribute("onclick", `deleteNote('${noteMessage}')`);
  deleteNoteBtn.append(deleteNoteBtnIcon);
  noteAndBtn.append(deleteNoteBtn);
  noteDiv.append(noteAndBtn);
  note.append(noteDiv);

  noteHolder.append(note);
}

function deleteNote(noteId) {
  let noteToDelete = document.getElementById(`${noteId}`);
  noteToDelete.remove();
  saveNotes();
}

function saveNotes() {
  let notes = document.getElementsByClassName("theNote");
  let noteList = [];
  for (let i of notes) {
    noteList.push(i.textContent);
  }
  let noteToSave = {
    key: sessionStorage.getItem("empno") + "note",
    notes: noteList,
  };
  fetch("/note/saveNote", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(noteToSave),
  });
}
let noteInput = document.getElementById("noteInput");
noteInput.addEventListener("keydown", function () {
  if (event.keyCode == 13) sendNote();
});

let addNoteBtn = document.getElementById("addNoteBtn");
addNoteBtn.addEventListener("click", sendNote);

function sendNote() {
  if (noteInput.value.trim() !== "") {
    addNote(noteInput.value.trim());
    saveNotes();
    noteInput.value = "";
  }
}

function getNote(key) {
  fetch(`/note/getNote?key=${key}`)
    .then((response) => response.json())
    .then((data) => {
      for (let i of data) {
        addNote(i);
      }
    });
}

fetch(
  "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-007?Authorization=CWB-9EE066BE-AB08-43BF-91C4-20969FF6AE4F&format=JSON&locationName=%E4%B8%AD%E5%A3%A2%E5%8D%80&elementName=WeatherDescription"
)
  .then((response) => response.json())
  .then((data) => {
    let allWeather =
      data.records.locations[0].location[0].weatherElement[0].time;

    document.getElementById("todayWeather").textContent =
      allWeather[0].elementValue[0].value;

    let count = 1;
    for (let i = 2; i < allWeather.length; i += 2) {
      let futureDays = document.getElementById(`futureDay${count}`);
      futureDays.firstElementChild.textContent = allWeather[i].startTime.substr(
        0,
        10
      );

      futureDays.lastElementChild.textContent =
        allWeather[i].elementValue[0].value;

      count++;
    }
  });

let noteKey = sessionStorage.getItem("empno") + "note";

getNote(noteKey);
