import { message } from "antd";

export const fetchUsers = async () => {
    try {
        const response = await fetch("/pa165/rest/users");
        let users = await response.json();
        users = users.map((user) => {
            return {
                ...user,
                key: user.id
            }
        })
        return users;
    } catch (error) {
        message.error(error.message);
    }
}

export const fetchBooks = async () => {
    try {
        const response = await fetch("/pa165/rest/books");
        let books = await response.json();
        books = books.map((book) => {
            return {
                ...book,
                key: book.id
            }
        })
        return books;
    } catch (error) {
        message.error(error.message);
    }
}

export const createBook = async (book) => {
    try {
        await fetch("/pa165/rest/books", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(book)
        })
        message.success('The book has been added.');
    } catch (error) {
        message.error(error.message);
    }
}

export const deleteBook = async (book) => {
    try {
        await fetch(`/pa165/rest/books?id=${book.id}`, {
            method: "DELETE"
        })
        message.success('The book has been deleted.');
    } catch (error) {
        message.error(error.message);
    }
}