import React from "react";
import Link from "next/link";
import styles from "../styles/dropDown.module.css";
const DropDown = ({ title, create, list, icon }) => {
  return (
    <div className="dropdown mt-1">
      <button
        className="btn btn-secondary dropdown-toggle"
        type="button"
        data-bs-toggle="dropdown"
        aria-expanded="false"
      >
        {title} {icon}
      </button>
      <ul className={`dropdown-menu ${styles.center}`}>
        <li>
          {create ? (
            <Link className="dropdown-item" href={`${create}`}>
              Criar{" "}
            </Link>
          ) : null}
        </li>
        <li>
          <Link className="dropdown-item" href={`${list}`}>
            Listar
          </Link>
        </li>
      </ul>
    </div>
  );
};

export default DropDown;
