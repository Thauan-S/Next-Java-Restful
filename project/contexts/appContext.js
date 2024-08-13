import React,{createContext}from 'react'
import { globalState ,urlPackage, urlReserve} from './data'

// verificar a dcoumentação next. Pelo teste que fiz só preciso da variável abaixo para utilizar o contexto
export const GlobalContext=createContext({globalState,urlPackage,urlReserve})
const AppContext = ({children}) => {

  return (
    <GlobalContext.provider>
        {children}
    </GlobalContext.provider>
  )
}

export default AppContext