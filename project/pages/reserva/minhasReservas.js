import TableReserves from '@/components/tableReserves'
import useMyReserves from '@/hooks/useMyReserves'
import React from 'react'



const MinhasReservas = () => {
  const{reserves,username}=useMyReserves()
  return (
    <TableReserves reserves={reserves} username={username}/>
  )

}

export default MinhasReservas