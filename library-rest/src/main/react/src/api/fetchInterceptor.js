import fetchIntercept from 'fetch-intercept';

export const unregister = fetchIntercept.register({
    request: function (url, config) {
        // Modify the url or config here
        // If running locally, map URLs to the value of REACT_APP_SERVER
        if (process.env.REACT_APP_SERVER && url.startsWith("/pa165/rest")) {
            url = process.env.REACT_APP_SERVER + url;
        }
        return [url, config];
    },

    requestError: function (error) {
        // Called when an error occured during another 'request' interceptor call
        return Promise.reject(error);
    },

    response: function (response) {
        // Modify the reponse object
        return response;
    },

    responseError: function (error) {
        // Handle an fetch error
        return Promise.reject(error);
    }
});
