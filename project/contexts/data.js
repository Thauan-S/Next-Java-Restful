// refazer com o useState para atualizar o nome e token : eventListener
export const globalState = {
  token:
    typeof window !== "undefined" ? window.localStorage.getItem("token") : null,
  username:
    typeof window !== "undefined"
      ? window.localStorage.getItem("username")
      : null,
  typeOfUser: 
  typeof window !== "undefined" ?window.localStorage.getItem("typeOfUser"): null
};
export const urlPackage = { url: "https://next-java-restful-tropical-back-end.onrender.com/api/travelPackages/v1" };
export const urlReserve = "https://next-java-restful-tropical-back-end.onrender.com/api/reserves/v1";


function checkLocalStorageUpdates() {
  const token = window.localStorage.getItem("token");
  const username = window.localStorage.getItem("username");
  const typeOfUser=window.localStorage.getItem("typeOfUser")
  if (globalState.token !== token || globalState.username !== username || globalState.typeOfUser!==typeOfUser) {
    globalState.token = token;
    globalState.username = username;
    globalState.typeOfUser=typeOfUser
  }
}


if (typeof window !== "undefined") {
  setInterval(checkLocalStorageUpdates, 1000);
}
