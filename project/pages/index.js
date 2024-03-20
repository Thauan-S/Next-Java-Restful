import { Inter } from "next/font/google";
import styles from "@/styles/Home.module.css";
import Card from "@/components/card";
import Carrousel from "@/components/carrousel";
import HeadComponent from "@/components/head";

const inter = Inter({ subsets: ["latin"] });

export default function Home() {
  let arrCards = [
    {
      image: "/img/praia.jpg",
      title: "Explore o mundo",
      description:
        "De praias paradisíacas a metrópoles vibrantes, nossa agência de viagens está pronta para tornar seus sonhos realidade.Seja qual for o seu desejo de aventura,estamos aqui para fazer cada momento ser memorável.Seja qual for o seu desejo de aventura.",
      buttonText: "Conferir Destinos",
    },
    {
      image: "/img/venda-com-desconto-especial-em-viagens.jpg",
      title: " Os melhores preços do mercado",
      description:
        "Oferecemos preços competitivos para tornar suas aventuras acessíveis e inesquecíveis. Com opções para todos os orçamentos, garantimos transparência e excelentes ofertas em passagens, hospedagem e pacotes turísticos.",
      buttonText: "Conferir Ofertas",
    },
    {
      image: "/img/primeiro-sinal-de-seguranca-na-natureza.jpg",
      title: " Segurança",
      description:
        " Na nossa agência de viagens, a segurança é a nossa prioridade número um. Nossos especialistas garantem viagens bem planejadas, parceiros confiáveis e aconselhamento sobre medidas de segurança em todos os destinos.",
    },
    {
      image: "/img/go.jpg",
      title: "Confiabilidade",
      description:
        " Aqui na nossa agência, colocamos a confiança no centro de tudo o que fazemos. Desde o momento em que você entra em contato conosco, até o dia em que você volta para casa após a viagem dos seus sonhos, estaremos ao seu lado, cuidando de todos os detalhes com diligência e cuidado.",
    },
  ];
  let arrCarrousel = [
    {
      title: "Explore locais incriveis",
      image: "/img/desert.jpg",
      active: "active",
    },
    { title: "Conheça o mundo", image: "/img/paisagemaaa.jpg" },
    {
      title: "Os mais variados locais",
      image: "/img/pexels-pixabay-279574.jpg",
    },
  ];
  return (
    <>
      <HeadComponent title={"Tropical | Início"} />

      <main className={` container-fluid ${styles.main} ${styles.div}  `}>
        <div className="row text-center  ">
          {arrCards.map((i, index) => (
            <div key={index} className="col-md mb-1">
              <Card
                image={i.image}
                title={i.title}
                description={i.description}
                btnText={i.buttonText}
              />
            </div>
          ))}
        </div>
        <div className="mt-5 ">
          <Carrousel arrCarrousel={arrCarrousel} />
        </div>
      </main>
    </>
  );
}
