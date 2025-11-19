import axios from "axios";

const bookImagesService = {
  async getBookImage(titulo) {
    try {
      const response = await axios.get(
        `https://www.googleapis.com/books/v1/volumes?q=intitle:${titulo}`
      );

      const items = response.data.items;

      if (!items || items.length === 0) return null;

      const image =
        items[0].volumeInfo.imageLinks?.thumbnail ||
        items[0].volumeInfo.imageLinks?.smallThumbnail ||
        null;

      return image;
    } catch (err) {
      console.log("Erro ao buscar imagem:", err);
      return null;
    }
  }
};

export default bookImagesService;
