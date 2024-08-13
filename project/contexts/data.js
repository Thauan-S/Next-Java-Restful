
export const globalState =  {
 token:typeof window !== 'undefined' ? window.localStorage.getItem('token') : null,
 username:typeof window !== 'undefined' ? window.localStorage.getItem('username') : null,
}
export const urlPackage={url:"http://localhost:80/api/pacotes/v1"}
export const urlReserve="http://localhost:80/api/reservas/v1"
   


