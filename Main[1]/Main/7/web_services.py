from flask import Flask, jsonify

app = Flask(__name__)

# Sample student data
students = [
    {'id': 1, 'name': 'Aniket', 'course': 'IT'},
    {'id': 2, 'name': 'Sneha', 'course': 'CS'},
    {'id': 3, 'name': 'Ravi', 'course': 'AI'}
]

@app.route('/students', methods=['GET'])
def get_students():
    return jsonify(students)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

