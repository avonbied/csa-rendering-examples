# Python 3 server example
from http.server import BaseHTTPRequestHandler, HTTPServer

HOST_NAME = ""
PORT = 8080

class MyServer(BaseHTTPRequestHandler):
	def do_GET(self):
		self.send_response(200)
		self.send_header("Content-type", "text/html")
		self.end_headers()
		if self.path == "/":
			with open("./index.html", "r") as f:
				self.wfile.write(bytes(f.read(), "utf-8"))
		elif self.path.startswith("/user"):
			with open("./templates/user.html", "r") as f:
				template = f.read()
				self.wfile.write(bytes(template.replace("{UserName}", self.path.removeprefix("/user/")), "utf-8"))
		else:
			self.path = "/"
			return self.do_GET()

if __name__ == "__main__":
	webServer = HTTPServer((HOST_NAME, PORT), MyServer)
	print(f"Server started http://{HOST_NAME}:{PORT}")

	try:
		webServer.serve_forever()
	except KeyboardInterrupt:
		pass

	webServer.server_close()
	print("Server stopped.")