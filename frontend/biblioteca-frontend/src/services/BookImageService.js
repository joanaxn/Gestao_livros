import axios from "axios";

const bookImagesService = {
  async getBookImage(titulo) {
    try {
      if (!titulo) return undefined;

      const query = encodeURIComponent(titulo);

      const res = await axios.get(
        `https://www.googleapis.com/books/v1/volumes?q=intitle:${query}`
      );

      const lista = res.data.items;
      if (!lista || lista.length === 0) return undefined;

      const info = lista[0].volumeInfo;

      if (info.imageLinks) {
        return (
          info.imageLinks.thumbnail ||
          info.imageLinks.smallThumbnail ||
          undefined
        );
      }

      return undefined;
    } catch (erro) {
      console.error("Erro ao buscar imagem:", erro);
      return undefined;
    }
  }
};

export default bookImagesService;
