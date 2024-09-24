/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  images: {
    domains: ['upload.wikimedia.org'], // Adiciona o domínio permitido
  },
}

module.exports = nextConfig