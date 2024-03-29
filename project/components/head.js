import React from "react";
import Head from "next/head";
const HeadComponent = ({ title, icon }) => {
  return (
    <Head>
      <title>{title}</title>
      <meta name="description" content="Generated by create next app" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <link rel="icon" href="/img/icone.png" />
    </Head>
  );
};

export default HeadComponent;
