import React from 'react';
import '../assets/react-toolbox/theme.css';
import theme from '../assets/react-toolbox/theme.js';
import ThemeProvider from 'react-toolbox/lib/ThemeProvider';

import '../css/App.css';

function App() {
  return (
    <ThemeProvider theme={theme}>
    <div className="App">
      <header>
        <p>Init</p>
      </header>
    </div>
    </ThemeProvider>
  );
}

export default App;
