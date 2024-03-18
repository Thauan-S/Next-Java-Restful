import React from "react";
import styles from "../styles/footer.module.css";
const Footer = () => {
  return (
    <footer className={`${styles.footer} bg-dark  text-white text-center`}>
      Tropicalagencia@exemplo.com
      <br />
      Tel : (00) 0000-0000
      <div className={`${styles.linhaFooter}`}>
        © Tropical Todos os direitos reservados
      </div>
    </footer>
  );
};

export default Footer;
