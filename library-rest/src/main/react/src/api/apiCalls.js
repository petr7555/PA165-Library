import { message } from "antd";

export const fetchUsers = async () => {
    try {
        const response = await fetch("/pa165/rest/users");

        if (!response.ok) {
            message.error("Couldn't fetch users.");
            return [];
        }

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

        if (!response.ok) {
            message.error("Couldn't fetch books.");
            return [];
        }

        let books = await response.json();
        books = books.map((book) => {
            return {
                ...book,
                key: book.id
            }
        });
        return books;
    } catch (error) {
        message.error(error.message);
    }
}

export const createBook = async (book) => {
    try {
        const response = await fetch("/pa165/rest/books", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(book)
        });
        response.ok ? message.success('The book has been added.') : message.error("The book couldn't be added.");
    } catch (error) {
        message.error(error.message);
    }
}

export const deleteBook = async (book) => {
    try {
        const response = await fetch(`/pa165/rest/books?id=${book.id}`, {
            method: "DELETE"
        });
        response.ok ? message.success('The book has been deleted.') : message.error('Cannot delete book.');
    } catch (error) {
        message.error(error.message);
    }
}
