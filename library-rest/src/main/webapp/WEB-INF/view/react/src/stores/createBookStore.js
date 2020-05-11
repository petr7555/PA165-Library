export default function createBookStore() {
    return {
        books: [],
        async fetchBooks() {
            try {
                // const res = await fetch(`http://localhost:8080/auctions/`);
                // const result = await res.json();
                const result = [
                    {
                        id: 1,
                        name: "Brand new fridge",
                        description: "Too big for my flat.",
                        user: "Anne",
                        until: "2020/05/05",
                        actual_price: 100,
                        winning_user: "John"
                    }
                ]
                this.books = result;
            } catch (error) {
                this.books = [];
            }
        }
    }
}
