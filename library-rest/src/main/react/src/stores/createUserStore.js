import { message } from "antd";

export function createUserStore() {
    return {
        user: {},
        authorities: [],
        booksInCart: [],
        books: [],
        myLoans: [],

        async fetchBooks() {
            const response = await fetch("/pa165/rest/books");
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
            const response = await fetch(`/pa165/rest/users?email=${email}`);
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
                await fetch("/pa165/rest/loans", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(loan)
                })
                message.success('Loan request has been submitted.');
                this.booksInCart = [];
            } catch (error) {
                message.error(error.message);
            }
        },

        async fetchMyLoans() {
            if (this.user.id === undefined) {
                setTimeout(() => this.fetchMyLoans(), 100);
                return [];
            }
            const response = await fetch(`/pa165/rest/singleLoans?userId=${this.user.id}`);
            this.myLoans = await response.json();
        }
    }
}
