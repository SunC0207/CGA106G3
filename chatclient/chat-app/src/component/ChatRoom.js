import React, { useState } from "react";

const chatRoom = () => {
  const [userData, setUserData] = useState({
    ename: "",
    recievername: "",
    connected: false,
    message: "",
  });
  return (
    <div className="container">
      {userData.connected ? (
        <div></div>
      ) : (
        <div className="register">
          <input
            id="userName"
            placeholder="Enter The UserName"
            value={userData.ename}
            onChange={handleUserName}
          />
<button type="button" onClick={registerUser}>
  連線
</button>

        </div>
      )}
    </div>
  );
};

export default chatRoom;
