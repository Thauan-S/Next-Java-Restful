import Image from "next/image";
import React from "react";

const Promocoes = () => {
  return (
    <main>
      <h1 className="bg-white text-center">Ofertas do dia</h1>
      <div id="carouselExample" className="carousel slide">
        <div className="carousel-inner">
          <div className="carousel-item active">
            <div className="row">
              <div className="card-group">
                <div className="card  border-info">
                  <Image
                    src="/img/cataratas.jpg"
                    className="card-img-top img-card"
                    alt="Cataratas do iguaçu"
                    layout="responsive" 
                    width={800} 
                    height={600}
                  />
                  <div className="card-body text-center">
                    <h5 className="card-title">Cataratas do iguaçu</h5>
                    <p className="card-text">
                      Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                      Sed auctor metus nec mauris sodales sollicitudin. Maecenas
                      placerat libero libero, vitae lacinia leo cursus sed.
                      Quisque vitae ligula ac enim bibendum convallis ut a
                      magna. Praesent quis aliquet purus. Proin sem sem,
                      lobortis eget sollicitudin vel, sollicitudin eget dui.
                    </p>
                  </div>
                  <div className="card-footer text-center">
                    <a href="#" className="btn  btn-info btnpromo ">
                      899,99 R$ /sem
                    </a>
                  </div>
                </div>
                <div className="card border-info">
                  <Image
                    src="/img/Sydney.jpg"
                    className="card-img-top img-card"
                    alt="Ópera de Sydney"
                    layout="responsive" 
                    width={800} 
                    height={600}
                    
                  />
                  <div className="card-body text-center">
                    <h5 className="card-title">Sydney Austrália</h5>
                    <p className="card-text">
                      Ut eu sapien ac eros porta laoreet. Vestibulum ante ipsum
                      primis in faucibus orci luctus et ultrices posuere cubilia
                      curae; Mauris libero ipsum, pulvinar non tristique at,
                      imperdiet id purus. Cras suscipit felis at quam posuere, a
                      tincidunt tellus vehicula. Donec gravida dictum diam quis
                      sagittis. Integer metus risus, viverra eu porttitor non,
                      malesuada a massa. Etiam bibendum scelerisque eleifend.
                      Fusce mauris leo, euismod ut ligula quis, ullamcorper
                      tristique dui.
                    </p>
                  </div>
                  <div className="card-footer text-center">
                    <a href="#" className="btn  btn-info btnpromo ">
                      1099,99 R$ /sem
                    </a>
                  </div>
                </div>
                <div className="card h-100 border-info ">
                  <Image
                    src="/img/Dubai.jpg"
                    className="card-img-top img-card"
                    alt="Foto do Burj Al Arab , um hotel em formato de vela de barco"
                    layout="responsive" 
                    width={800} 
                    height={600}
                  />
                  <div className="card-body text-center">
                    <h5 className="card-title">Dubai</h5>
                    <p className="card-text">
                      Vestibulum ac tristique lacus, ut vehicula lacus. Duis
                      cursus nisi non neque lacinia, varius mattis est lobortis.
                      Duis malesuada odio nec dui iaculis pretium. Donec ac mi
                      massa. Fusce at lacus erat. Nulla eu orci tortor. Mauris
                      sagittis lacinia malesuada. Phasellus vel odio vitae nisl
                      tincidunt finibus. Integer non sapien in enim mattis
                      mattis.
                    </p>
                  </div>
                  <div className="card-footer text-center">
                    <a href="#" className="btn  btn-info btnpromo ">
                      1199,99 R$ /sem
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="carousel-item">
            <div className="row">
              <div className="card-group">
                <div className="card border-info">
                  <Image
                    src="/img/Sydney.jpg"
                    className="card-img-top img-card"
                    alt="..."
                    layout="responsive" 
                    width={800} 
                    height={600}
                  />
                  <div className="card-body text-center">
                    <h5 className="card-title">TSydney Austrália</h5>
                    <p className="card-text">
                      This is a wider card with supporting text below as a
                      natural lead-in to additional content. This content is a
                      little bit longer.
                    </p>
                  </div>
                  <div className="card-footer text-center">
                    <a href="#" className="btn  btn-info btnpromo ">
                      899,99 R$ /sem
                    </a>
                  </div>
                </div>
                <div className="card border-info">
                  <Image
                    src="/img/Sydney.jpg"
                    className="card-img-top img-card"
                    alt="..."
                    layout="responsive" 
                    width={800} 
                    height={600}
                  />
                  <div className="card-body text-center">
                    <h5 className="card-title">Sydney Austrália</h5>
                    <p className="card-text">
                      This card has supporting text below as a natural lead-in
                      to additional content.
                    </p>
                  </div>
                  <div className="card-footer text-center">
                    <a href="#" className="btn  btn-info btnpromo ">
                      899,99 R$ /sem
                    </a>
                  </div>
                </div>
                <div className="card border-info">
                  <Image
                    src="/img/Sydney.jpg"
                    className="card-img-top img-card"
                    alt="..."
                    layout="responsive" 
                    width={800} 
                    height={600}
                  />
                  <div className="card-body text-center">
                    <h5 className="card-title">Sydney Austrália</h5>
                    <p className="card-text">
                      This is a wider card with supporting text below as a
                      natural lead-in to additional content. This card has even
                      longer content than the first to show that equal height
                      action.
                    </p>
                  </div>
                  <div className="card-footer text-center">
                    <a href="#" className="btn  btn-info btnpromo ">
                      899,99 R$ /sem
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="carousel-item">
            <div className="row">
              <div className="card-group">
                <div className="card border-info">
                  <Image
                    src="/img/Dubai.jpg"
                    className="card-img-top img-card"
                    alt="..."
                    layout="responsive" 
                    width={800} 
                    height={600}
                  />
                  <div className="card-body text-center">
                    <h5 className="card-title">Dubai</h5>
                    <p className="card-text">
                      This is a wider card with supporting text below as a
                      natural lead-in to additional content. This content is a
                      little bit longer.
                    </p>
                  </div>
                  <div className="card-footer text-center">
                    <a href="#" className="btn  btn-info btnpromo ">
                      899,99 R$ /sem
                    </a>
                  </div>
                </div>
                <div className="card  border-info  ">
                  <Image
                    src="/img/Dubai.jpg"
                    className="card-img-top img-card "
                    alt="..."
                    layout="responsive" 
                    width={800} 
                    height={600}
                  />
                  <div className="card-body text-center">
                    <h5 className="card-title">Dubai</h5>
                    <p className="card-text">
                      This card has supporting text below as a natural lead-in
                      to additional content.
                    </p>
                  </div>
                  <div className="card-footer text-center">
                    <a href="#" className="btn  btn-info btnpromo ">
                      899,99 R$ /sem
                    </a>
                  </div>
                </div>
                <div className="card border-info">
                  <Image
                    src="/img/Dubai.jpg"
                    className="card-img-top img-card"
                    alt="..."
                    layout="responsive" 
                    width={800} 
                    height={600}
                  />
                  <div className="card-body text-center">
                    <h5 className="card-title">Dubai</h5>
                    <p className="card-text">
                      This is a wider card with supporting text below as a
                      natural lead-in to additional content. This card has even
                      longer content than the first to show that equal height
                      action.
                    </p>
                  </div>
                  <div className="card-footer text-center">
                    <a href="#" className="btn  btn-info btnpromo ">
                      899,99 R$ /sem
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        {/* botões  */}
        <div className="botões mt-5">
          <button
            className="carousel-control-prev prevNext"
            type="button"
            data-bs-target="#carouselExample"
            data-bs-slide="prev"
          >
            <span
              className="carousel-control-prev-icon bg-dark"
              aria-hidden="true"
            />
            <span className="visually-hidden">Previous</span>
          </button>
          <button
            className="carousel-control-next prevNext"
            type="button"
            data-bs-target="#carouselExample"
            data-bs-slide="next"
          >
            <span
              className="carousel-control-next-icon bg-dark"
              aria-hidden="true"
            />
            <span className="visually-hidden">Next</span>
          </button>
        </div>
      </div>
    </main>
  );
};

export default Promocoes;
