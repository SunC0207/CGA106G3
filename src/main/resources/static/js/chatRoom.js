window.onload = function () {
  
  connect();
};
let chatBar = document.getElementById("chatBar");
chatBar.addEventListener("click", function () {
  $("#stikerHolder").removeClass("showStiker");
  $("#chat").toggleClass("showChat");
});

let stompClient = null;
let username = null;
let userId = null;

let chatInput = document.getElementById("chatInput");

let chatArea = document.getElementById("messageArea");
let scroll = document.getElementById("scroll");

function connect(event) {
  username = sessionStorage.getItem("ename");
  userId = sessionStorage.getItem("empno");
  let socket = new SockJS("/ws");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, onConnect, onError);
  loadHistory();
}

function onConnect() {
  stompClient.subscribe("/chatroom/public", onMessageReceived);
}
function onError(error) {
  console.log("error");
}

function send(event) {
  let messageContext = chatInput.value.trim();

  if (messageContext && stompClient) {
    let messageSend = {
      senderId: sessionStorage.getItem("empno"),
      senderName: username,
      message: chatInput.value,
      status: "MESSAGE",
      date: moment().format("YYYY-MM-D kk:mm"),
    };
    stompClient.send("/app/message", {}, JSON.stringify(messageSend));
    chatInput.value = "";
  }
}

function loadHistory() {
  fetch("/getPublicHistory")
    .then((response) => response.json())
    .then((data) => {
      for (let message of data) {
        let messageElement = document.createElement("li");
        if (message.senderId == sessionStorage.getItem("empno")) {
          messageElement.classList.add("my-message");

          let spans = document.createElement("div");

          let sendtime = document.createElement("span");
          sendtime.textContent = message.date;
          sendtime.setAttribute("class", "msgtime");

          spans.appendChild(sendtime);

          messageElement.appendChild(spans);
          let messagelog = document.createElement("div");
          if (message.status === "PIC") {
            messagelog.setAttribute("class", "alert alert-danger mb-0 ");
            let img = document.createElement("img");
            img.setAttribute("src", message.message);
            img.setAttribute("style", "width:100%;");
            messagelog.appendChild(img);
          } else if (message.status === "MESSAGE") {
            messagelog.setAttribute("class", "alert alert-danger mb-0 ");
            messagelog.textContent = message.message;
          } else if (message.status === "STIKER") {
            let img = document.createElement("img");
            img.setAttribute("src", message.message);
            img.setAttribute("style", "width:200px;");
            messagelog.appendChild(img);
          }

          messageElement.appendChild(messagelog);
        } else {
          messageElement.classList.add("other-message");

          let spans = document.createElement("div");
          let sender = document.createElement("span");
          sender.textContent = message.senderName;
          spans.appendChild(sender);

          let sendtime = document.createElement("span");
          sendtime.textContent = message.date;
          sendtime.setAttribute("class", "msgtime");
          spans.appendChild(sendtime);

          messageElement.appendChild(spans);
          let messagelog = document.createElement("div");
          if (message.status === "PIC") {
            messagelog.setAttribute("class", "alert alert-dark mb-0 ");
            let img = document.createElement("img");
            img.setAttribute("src", message.message);
            img.setAttribute("style", "width:100%;");
            messagelog.appendChild(img);
          } else if (message.status === "MESSAGE") {
            messagelog.setAttribute("class", "alert alert-dark mb-0 ");
            messagelog.textContent = message.message;
          } else if (message.status === "STIKER") {
            let img = document.createElement("img");
            img.setAttribute("src", message.message);
            img.setAttribute("style", "width:200px;");
            messagelog.appendChild(img);
          }

          messageElement.appendChild(messagelog);
        }
        chatArea.appendChild(messageElement);
      }
      scroll.scrollTop = scroll.scrollHeight;
    });
}

function onMessageReceived(payload) {
  let message = JSON.parse(payload.body);

  let messageElement = document.createElement("li");
  if (message.senderId == sessionStorage.getItem("empno")) {
    messageElement.classList.add("my-message");

    let spans = document.createElement("div");

    let sendtime = document.createElement("span");
    sendtime.textContent = message.date;
    sendtime.setAttribute("class", "msgtime");

    spans.appendChild(sendtime);

    messageElement.appendChild(spans);
    let messagelog = document.createElement("div");
    if (message.status === "PIC") {
      messagelog.setAttribute("class", "alert alert-danger mb-0 ");
      let img = document.createElement("img");
      img.setAttribute("src", message.message);
      img.setAttribute("style", "width:100%;");
      messagelog.appendChild(img);
    } else if (message.status === "MESSAGE") {
      messagelog.setAttribute("class", "alert alert-danger mb-0 ");
      messagelog.textContent = message.message;
    } else if (message.status === "STIKER") {
      let img = document.createElement("img");
      img.setAttribute("src", message.message);
      img.setAttribute("style", "width:200px;");
      messagelog.appendChild(img);
    }

    messageElement.appendChild(messagelog);
  } else {
    messageElement.classList.add("other-message");

    let spans = document.createElement("div");
    let sender = document.createElement("span");
    sender.textContent = message.senderName;
    spans.appendChild(sender);

    let sendtime = document.createElement("span");
    sendtime.textContent = message.date;
    sendtime.setAttribute("class", "msgtime");
    spans.appendChild(sendtime);

    messageElement.appendChild(spans);
    let messagelog = document.createElement("div");
    if (message.status === "PIC") {
      messagelog.setAttribute("class", "alert alert-dark mb-0 ");
      let img = document.createElement("img");
      img.setAttribute("src", message.message);
      img.setAttribute("style", "width:100%;");
      messagelog.appendChild(img);
    } else if (message.status === "MESSAGE") {
      messagelog.setAttribute("class", "alert alert-dark mb-0 ");
      messagelog.textContent = message.message;
    } else if (message.status === "STIKER") {
      let img = document.createElement("img");
      img.setAttribute("src", message.message);
      img.setAttribute("style", "width:200px;");
      messagelog.appendChild(img);
    }

    messageElement.appendChild(messagelog);
  }
  chatArea.appendChild(messageElement);

  scroll.scrollTop = scroll.scrollHeight;
}

function ChooseImage() {
  document.getElementById("imageFile").click();
}

function SendImage(event) {
  let file = event.files[0];

  if (!file.type.match("image.*")) {
    alert("只接受圖片檔");
  } else {
    let reader = new FileReader();

    reader.addEventListener(
      "load",
      function () {
        let imgMessage = {
          senderId: sessionStorage.getItem("empno"),
          senderName: username,
          message: reader.result,
          status: "PIC",
          date: moment().format("YYYY-MM-D kk:mm"),
        };
        stompClient.send("/app/message", {}, JSON.stringify(imgMessage));
      },
      false
    );

    if (file) {
      reader.readAsDataURL(file);
    }
  }
  scroll.scrollTop = scroll.scrollHeight;
}
let stikerHolder = document.getElementById("stikerHolder");

document.getElementById("stiker").addEventListener("click", function () {
  $("#stikerHolder").toggleClass("showStiker");
});

function sendStiker(src) {
  let stikerMessage = {
    senderId: sessionStorage.getItem("empno"),
    senderName: username,
    message: src,
    status: "STIKER",
    date: moment().format("YYYY-MM-D kk:mm"),
  };
  stompClient.send("/app/message", {}, JSON.stringify(stikerMessage));
  chatInput.value = "";
}
