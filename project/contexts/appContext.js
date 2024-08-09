import React,{createContext,useState}from 'react'
import { globalState ,urlPackage} from './data'

// verificar a dcoumentação next. Pelo teste que fiz só preciso da variável abaixo para utilizar o contexto
export const GlobalContext=createContext({globalState,urlPackage})
const AppContext = ({children}) => {

  return (
    <GlobalContext.provider>
        {children}
    </GlobalContext.provider>
  )
}

export default AppContext