import React from 'react';
import { createRoot } from 'react-dom/client';
import { Provider } from 'react-redux';
import { store } from './app/store';
import Main from './components/Main';
import reportWebVitals from './reportWebVitals';
import './css/index.css';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import NotFound from './components/NotFound';
import Login from './components/Login';

const container = document.getElementById('root');
const root = createRoot(container);

root.render(
  <Router>
    <Provider store={store}>
      <Routes>

        <Route path='/' element={<Main />} />
        <Route path='/login' element={<Login />} />

        <Route path='/*' element={<NotFound />} />
      </Routes>
    </Provider>
  </Router>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
