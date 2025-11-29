import './StatDisplay.scss'

var money = 0,
    addition = 1.0.toFixed(1),
    multiplier = 1.0.toFixed(1),
    nlogn = 1.0.toFixed(1),
    n_squared = 1.0.toFixed(1),
    two_power_n = 1.0.toFixed(1),
    factorial = 1.0.toFixed(1);

export default function StatDisplay() {
    return (
        <div className="stat-display">
            <div className="money">
                <h1>${money}</h1>
            </div>
            <div className="stats">
                <div className="stat addition">+{addition}</div>
                <div className="stat multiplier">x{multiplier}</div>
                <div className="stat nlogn">{nlogn}log{nlogn}</div>
                <div className="stat n_squared">{n_squared}^2</div>
                <div className="stat two_power_n">2^{two_power_n}</div>
                <div className="stat factorial">{factorial}!</div>
            </div>
        </div>
    );
}