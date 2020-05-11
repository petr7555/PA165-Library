export function createUserStore() {
    return {
        username: "",
        authorities: "",
        booksInCart: [],
        books: [
            {
                id: 1,
                title: "Animal Farm",
                author: "George Orwell",
                available: true
            },
            {
                id: 2,
                title: "1984",
                author: "George Orwell",
                available: true
            },
            {
                id: 3,
                title: "Ostře sledované vlaky",
                author: "Bohumil Hrabal",
                available: false
            }
        ]

    }
}
