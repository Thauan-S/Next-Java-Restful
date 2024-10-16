import React, { useEffect, useState } from "react";
import Link from "next/link";
import Image from "next/image";
const Carrousel = ({ arrCarrousel }) => {
  if (arrCarrousel) {
    return (
      <>
        <div id="carouselExampleCaptions" className="carousel slide ">
          <div className="carousel-indicators ">
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to={"0"}
              className="active"
              aria-current="true"
              aria-label="Slide 1"
            />
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to={"1"}
              aria-label="Slide 2"
            />
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to={"2"}
              aria-label="Slide 3"
            />
          </div>
          <div className="rounded carousel-inner">
            {arrCarrousel.map((i, index) => (
              <div key={index} className={`carousel-item ${i.active} `}>
                <Image
                  src={i.image}
                  className="d-block  w-100"
                  alt="..."
                  layout="responsive"
                  width={800}
                  height={600}
                />
                <div className="carousel-caption d-none d-md-block">
                  <h5 className="text-dark">{i.title}</h5>
                </div>
              </div>
            ))}
          </div>
          <button
            className="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="prev"
          >
            <span
              className="carousel-control-prev-icon bg-dark"
              aria-hidden="true"
            />
            <span className="visually-hidden">Previous</span>
          </button>
          <button
            className="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="next"
          >
            <span
              className="carousel-control-next-icon bg-dark"
              aria-hidden="true"
            />
            <span className="visually-hidden">Next</span>
          </button>
        </div>
      </>
    );
  }
};

export default Carrousel;
