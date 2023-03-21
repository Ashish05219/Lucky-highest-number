import React, { useState, useEffect } from 'react';

const LeftTable = ({ onSelect }) => {
  const [numbers, setNumbers] = useState([]);

  useEffect(() => {
    const timer = setInterval(() => {
      const newNumbers = Array.from({ length: 8 }, () => Math.floor(Math.random() * (1999 - 1001 + 1)) + 1001);
      setNumbers(newNumbers);
    }, 400);

    return () => clearInterval(timer);
  }, []);

  const handleClick = (number) => {
    onSelect(number);
  };

  return (
    <table>
      <thead>
        <tr>
          <th>Left Table</th>
        </tr>
      </thead>
      <tbody>
        {numbers.map((number, index) => (
          <tr key={index}>
            <td>
              <button onClick={() => handleClick(number)}>{number}</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default LeftTable;
