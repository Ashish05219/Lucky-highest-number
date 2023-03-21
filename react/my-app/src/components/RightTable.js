import React from 'react';

const RightTable = ({ numbers }) => {
  return (
    <table>
      <thead>
        <tr>
          <th>Right Table</th>
        </tr>
      </thead>
      <tbody>
        {numbers.map((number, index) => (
          <tr key={index}>
            <td>{number}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default RightTable;
