if (!sessionStorage.getItem("loginOrNot")) {
  alert("請先登入");
  location.replace("EmpLogin.html");
}

let sidebar = document.getElementById("sidebar");
let perList = sessionStorage.getItem("perList");

for (let per of perList) {
  switch (per) {
    case "1":
      let side1 = document.createElement("a");
      let icon1 = document.createElement("i");
      let toggle1 = document.createElement("div");
      toggle1.setAttribute("class", "nav-item dropdown");
      side1.setAttribute("class", "nav-link dropdown-toggle");
      side1.setAttribute("data-bs-toggle", "dropdown");
      side1.setAttribute("href", "#");
      icon1.setAttribute("class", "bi bi-tools");
      side1.append(icon1);
      side1.append(" 服務管理");
      toggle1.append(side1);
      let toggleList1 = document.createElement("div");
      toggleList1.setAttribute(
        "class",
        "dropdown-menu bg-transparent border-0"
      );
      let toggleList11 = document.createElement("a");
      toggleList11.setAttribute("href", "memoitemcate.html");
      toggleList11.setAttribute("class", "dropdown-item");
      toggleList11.textContent = "追思品類別管理";

      let toggleList12 = document.createElement("a");
      toggleList12.setAttribute("href", "memoitemManage.html");
      toggleList12.setAttribute("class", "dropdown-item");
      toggleList12.textContent = "追思品管理";

      let toggleList13 = document.createElement("a");
      toggleList13.setAttribute("href", "religion.html");
      toggleList13.setAttribute("class", "dropdown-item");
      toggleList13.textContent = "方案宗教管理";

      let toggleList14 = document.createElement("a");
      toggleList14.setAttribute("href", "process.html");
      toggleList14.setAttribute("class", "dropdown-item");
      toggleList14.textContent = "方案流程管理";

      let toggleList15 = document.createElement("a");
      toggleList15.setAttribute("href", "ceremony.html");
      toggleList15.setAttribute("class", "dropdown-item");
      toggleList15.textContent = "方案儀式管理";

      let toggleList16 = document.createElement("a");
      toggleList16.setAttribute("href", "item.html");
      toggleList16.setAttribute("class", "dropdown-item");
      toggleList16.textContent = "方案細項管理";

      toggleList1.append(toggleList11);
      toggleList1.append(toggleList12);
      toggleList1.append(toggleList13);
      toggleList1.append(toggleList14);
      toggleList1.append(toggleList15);
      toggleList1.append(toggleList16);
      toggle1.append(toggleList1);
      sidebar.append(toggle1);
      break;
    case "2":
      let side2 = document.createElement("a");
      let icon2 = document.createElement("i");
      side2.setAttribute("class", "nav-item nav-link");
      side2.setAttribute("href", "addorder.html");
      icon2.setAttribute("class", "bi bi-folder-plus");
      side2.append(icon2);
      side2.append(" 禮儀服務");
      sidebar.append(side2);
      break;
    case "3":
      let side3 = document.createElement("a");
      let icon3 = document.createElement("i");
      let toggle3 = document.createElement("div");
      toggle3.setAttribute("class", "nav-item dropdown");
      side3.setAttribute("class", "nav-link dropdown-toggle");
      side3.setAttribute("data-bs-toggle", "dropdown");
      side3.setAttribute("href", "#");
      icon3.setAttribute("class", "bi bi-pencil-square");
      side3.append(icon3);
      side3.append(" 行程查詢");
      toggle3.append(side3);
      let toggleList3 = document.createElement("div");
      toggleList3.setAttribute(
        "class",
        "dropdown-menu bg-transparent border-0"
      );
      let toggleList31 = document.createElement("a");
      toggleList31.setAttribute("href", "empschedule.html");
      toggleList31.setAttribute("class", "dropdown-item");
      toggleList31.textContent = "個人排程瀏覽";
      let toggleList32 = document.createElement("a");
      toggleList32.setAttribute("href", "locschedule.html");
      toggleList32.setAttribute("class", "dropdown-item");
      toggleList32.textContent = "場地排程瀏覽";
      toggleList3.append(toggleList31);
      toggleList3.append(toggleList32);
      toggle3.append(toggleList3);
      sidebar.append(toggle3);
      break;
    case "4":
      let side4 = document.createElement("a");
      let icon4 = document.createElement("i");
      let toggle4 = document.createElement("div");
      toggle4.setAttribute("class", "nav-item dropdown");
      side4.setAttribute("class", "nav-link dropdown-toggle");
      side4.setAttribute("data-bs-toggle", "dropdown");
      side4.setAttribute("href", "#");
      icon4.setAttribute("class", "bi bi-pencil-square");
      side4.append(icon4);
      side4.append(" 訂單管理");
      toggle4.append(side4);
      let toggleList4 = document.createElement("div");
      toggleList4.setAttribute(
        "class",
        "dropdown-menu bg-transparent border-0"
      );
      let toggleList41 = document.createElement("a");
      toggleList41.setAttribute("href", "POrd.html");
      toggleList41.setAttribute("class", "dropdown-item");
      toggleList41.textContent = "方案訂單管理";
      let toggleList42 = document.createElement("a");
      toggleList42.setAttribute("href", "ordDetail.html");
      toggleList42.setAttribute("class", "dropdown-item");
      toggleList42.textContent = "追思品訂單管理";
      toggleList4.append(toggleList41);
      toggleList4.append(toggleList42);
      toggle4.append(toggleList4);
      sidebar.append(toggle4);
      break;
    case "5":
      let side5 = document.createElement("a");
      let icon5 = document.createElement("i");
      side5.setAttribute("class", "nav-item nav-link");
      side5.setAttribute("href", "memberManage.html");
      icon5.setAttribute("class", "bi bi-folder-plus");
      side5.append(icon5);
      side5.append("會員管理");
      sidebar.append(side5);
      break;
    case "6":
      let side6 = document.createElement("a");
      let icon6 = document.createElement("i");
      let toggle6 = document.createElement("div");
      toggle6.setAttribute("class", "nav-item dropdown");
      side6.setAttribute("class", "nav-link dropdown-toggle");
      side6.setAttribute("data-bs-toggle", "dropdown");
      side6.setAttribute("href", "#");
      icon6.setAttribute("class", "bi bi-pencil-square");
      side6.append(icon6);
      side6.append(" 網站管理");
      toggle6.append(side6);
      let toggleList6 = document.createElement("div");
      toggleList6.setAttribute(
        "class",
        "dropdown-menu bg-transparent border-0"
      );
      let toggleList61 = document.createElement("a");
      toggleList61.setAttribute("href", "infoManage.html");
      toggleList61.setAttribute("class", "dropdown-item");
      toggleList61.textContent = "網站資訊管理";
      let toggleList62 = document.createElement("a");
      toggleList62.setAttribute("href", "faqback.html");
      toggleList62.setAttribute("class", "dropdown-item");
      toggleList62.textContent = "常見問題管理";
      toggleList6.append(toggleList61);
      toggleList6.append(toggleList62);
      toggle6.append(toggleList6);
      sidebar.append(toggle6);
      break;
    case "7":
      let side7 = document.createElement("a");
      let icon7 = document.createElement("i");
      let toggle7 = document.createElement("div");
      toggle7.setAttribute("class", "nav-item dropdown");
      side7.setAttribute("class", "nav-link dropdown-toggle");
      side7.setAttribute("data-bs-toggle", "dropdown");
      side7.setAttribute("href", "#");
      icon7.setAttribute("class", "bi bi-pencil-square");
      side7.append(icon7);
      side7.append(" 後台管理");
      toggle7.append(side7);
      let toggleList7 = document.createElement("div");
      toggleList7.setAttribute(
        "class",
        "dropdown-menu bg-transparent border-0"
      );
      let toggleList71 = document.createElement("a");
      toggleList71.setAttribute("href", "empManage.html");
      toggleList71.setAttribute("class", "dropdown-item");
      toggleList71.textContent = "員工管理";
      let toggleList72 = document.createElement("a");
      toggleList72.setAttribute("href", "locManage.html");
      toggleList72.setAttribute("class", "dropdown-item");
      toggleList72.textContent = "場地管理";
      toggleList7.append(toggleList71);
      toggleList7.append(toggleList72);
      toggle7.append(toggleList7);
      sidebar.append(toggle7);
      break;
  }
}

document.getElementById("ename").textContent = sessionStorage.getItem("ename");
document.getElementById("logOut").addEventListener("click", () => {
  sessionStorage.clear();
  location.replace("/EmpLogin.html");
});
