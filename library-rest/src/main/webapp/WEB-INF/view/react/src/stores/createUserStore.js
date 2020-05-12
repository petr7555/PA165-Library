import { message } from "antd";

export function createUserStore() {
    return {
        user: {},
        authorities: "",
        booksInCart: [],
        books: [],
        myLoans: [],

        async fetchBooks() {
            const response = await fetch("http://localhost:8080/pa165/rest/books");
            const books = await response.json();
            this.books = books.map(item => {
                return {
                    ...item,
                    available: item.available &&
                        !this.booksInCart.some(bookInCart => bookInCart.id === item.id)
                }
            })
        },

        async fetchFullUserInfo(email) {
            let url = new URL('http://localhost:8080/pa165/rest/users');
            url.searchParams.set('email', email);
            const response = await fetch(url);
            this.user = await response.json();
        },

        addToCart(book) {
            this.booksInCart = [...this.booksInCart, book];
            this.books = this.books.map((item) => {
                return {
                    ...item,
                    available: item.available && (item.id !== book.id)
                }
            })
        },

        removeFromCart(book) {
            this.booksInCart = this.booksInCart.filter((item) => item.id !== book.id);
            this.books = this.books.map((item) => {
                return {
                    ...item,
                    available: item.available || (item.id === book.id)
                }
            })
        },

        async submitRequest() {
            const singleLoans = this.booksInCart.map(book => {
                return {
                    book: {
                        id: book.id
                    },
                    user: {
                        id: this.user.id
                    }
                }
            });
            const loan = {singleLoans};
            try {
                await fetch("http://localhost:8080/pa165/rest/loans", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(loan)
                })
                message.success('Loan request has been submitted.');
                this.booksInCart = [];
            } catch (error) {
                message.error(error.message());
            }
        },

        async fetchMyLoans() {
            let url = new URL('http://localhost:8080/pa165/rest/singleLoans');
            url.searchParams.set('userId', this.user.id);
            const response = await fetch(url);
            this.myLoans = await response.json();
        }
    }
}
