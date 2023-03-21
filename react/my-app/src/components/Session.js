import React, { useState, useEffect } from 'react';
import axios from 'axios';
import LeftTable from '../components/LeftTable';
import RightTable from '../components/RightTable';
import Modal from '../components/Modal';

const Session = () => {
  const [session, setSession] = useState({});
  const [numbers, setNumbers] = useState([]);
  const [selectedNumbers, setSelectedNumbers] = useState([]);
  const [sumOfSelectedNumbers, setSumOfSelectedNumbers] = useState(0);
  const [timeTaken, setTimeTaken] = useState(0);
  const [name, setName] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [modalMessage, setModalMessage] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios('/api/numbers');
      setNumbers(result.data);
    };
    fetchData();
  }, []);

  const handleSelectNumber = (number) => {
    if (selectedNumbers.includes(number)) {
      return;
    }

    const newSelectedNumbers = [...selectedNumbers, number];
    setSelectedNumbers(newSelectedNumbers);
    setSumOfSelectedNumbers(newSelectedNumbers.reduce((a, b) => a + b, 0));

    const newNumbers = numbers.filter((n) => n !== number);
    setNumbers(newNumbers);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const result = await axios.post('/api/session', {
      name,
      startTime: new Date().getTime(),
    });
    setSession(result.data);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setSession({});
    setNumbers([]);
    setSelectedNumbers([]);
    setSumOfSelectedNumbers(0);
    setTimeTaken(0);
    setName('');
  };

  useEffect(() => {
    if (sumOfSelectedNumbers >= 15600) {
      const endTime = new Date().getTime();
      setTimeTaken((endTime - session.startTime) / 1000);
      setModalMessage(`Time taken: ${timeTaken.toFixed(2)} seconds. Sum of selected numbers: ${sumOfSelectedNumbers}`);
      setShowModal(true);
    } else if (numbers.length === 0 && sumOfSelectedNumbers < 15600) {
      setModalMessage('You are unable to achieve your goal.');
      setShowModal(true);
    }
  }, [sumOfSelectedNumbers, numbers]);

  return (
    <div className="container">
      <div className="row mt-5">
        <div className="col-6">
          <LeftTable numbers={numbers} handleSelectNumber={handleSelectNumber} />
        </div>
        <div className="col-6">
          <RightTable selectedNumbers={selectedNumbers} />
        </div>
      </div>
      <div className="row mt-5">
        <div className="col-12">
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label htmlFor="name">Enter your name:</label>
              <input
                type="text"
                className="form-control"
                id="name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
              />
            </div>
            <button type="submit" className="btn btn-primary">
              Start session
            </button>
          </form>
        </div>
      </div>
      {showModal && (
        <Modal message={modalMessage} handleCloseModal={handleCloseModal} />
      )}
    </div>
  );
};

export default Session;
