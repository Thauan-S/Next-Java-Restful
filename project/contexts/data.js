// refazer com o useState para atualizar o nome e token : eventListener
export const globalState = {
  token:
    typeof window !== "undefined" ? window.localStorage.getItem("token") : null,
  username:
    typeof window !== "undefined"
      ? window.localStorage.getItem("username")
      : null,
};
export const urlPackage = { url: "http://localhost:80/api/pacotes/v1" };
export const urlReserve = "http://localhost:80/api/reservas/v1";


function checkLocalStorageUpdates() {
  const token = window.localStorage.getItem("token");
  const username = window.localStorage.getItem("username");

  if (globalState.token !== token || globalState.username !== username) {
    globalState.token = token;
    globalState.username = username;
  }
}


if (typeof window !== "undefined") {
  setInterval(checkLocalStorageUpdates, 1000);
}