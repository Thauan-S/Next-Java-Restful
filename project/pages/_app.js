import NavBar from "@/components/navBar";
import Footer from "@/components/footer";

import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/css/bootstrap.min.css";
//import "bootstrap/dist/js/bootstrap.bundle.min.js"; //<- parte que mencionei no readme.md ,comentei por que está gerando bugs
import "@/styles/globals.css";
import { useEffect } from "react";

export default function App({ Component, pageProps }) {
  useEffect(()=>{
    require("bootstrap/dist/js/bootstrap.bundle.min.js")
  },[])
  return (
    <div>
      <NavBar />
      <Component {...pageProps} />
      <Footer />
    </div>
  );
}
