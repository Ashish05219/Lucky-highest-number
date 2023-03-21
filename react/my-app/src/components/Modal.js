import React from 'react';

const Modal = ({ show, handleClose, timeTaken, sumOfSelectedNumbers, goalReached }) => {
  const showHideClassName = show ? 'modal display-block' : 'modal display-none';

  return (
    <div className={showHideClassName}>
      <section className="modal-main">
        <h2>{goalReached ? 'Congratulations!' : 'Oops!'}</h2>
        <p>{goalReached ? `You've reached your goal in ${timeTaken} seconds!` : 'You were unable to achieve your goal.'}</p>
        {goalReached && <p>Sum of selected numbers: {sumOfSelectedNumbers}</p>}
        <button onClick={handleClose}>Close</button>
      </section>
    </div>
  );
};

export default Modal;
