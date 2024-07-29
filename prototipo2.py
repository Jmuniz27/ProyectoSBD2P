import mysql.connector
from mysql.connector import Error
import tkinter as tk
from tkinter import ttk

def execute_query(query):
    connection = None  # Inicializa la variable connection como None
    try:
        connection = mysql.connector.connect(
            host='proyectosbdgrupo8.mysql.database.azure.com',
            user='main',
            password='proyectoSBD=Grupo8_',
            database='wuanplus'
        )

        if connection.is_connected():
            cursor = connection.cursor()
            cursor.execute(query)
            columns = cursor.column_names
            rows = cursor.fetchall()
            update_table(columns, rows)

    except Error as e:
        print(f"Error al conectar a MySQL: {e}")
    finally:
        if connection and connection.is_connected():
            connection.close()

def update_table(columns, rows):
    for col in tree.get_children():
        tree.delete(col)
    
    tree["columns"] = columns
    for col in columns:
        tree.heading(col, text=col)
        tree.column(col, width=100, anchor='center')

    for row in rows:
        tree.insert("", "end", values=row)

    for col in columns:
        tree.column(col, stretch=tk.YES)

def query1():
    query = "SELECT * FROM EMPLEADO LIMIT 10"
    execute_query(query)

def query2():
    query = "SELECT * FROM FACTURA LIMIT 10"
    execute_query(query)

app = tk.Tk()
app.title("Visualizador de Base de Datos MySQL")
app.geometry("800x600")

frame = tk.Frame(app)
frame.pack(pady=20)

btn_query1 = tk.Button(frame, text="Ejecutar Consulta 1", command=query1)
btn_query1.pack(side="left", padx=10)

btn_query2 = tk.Button(frame, text="Ejecutar Consulta 2", command=query2)
btn_query2.pack(side="left", padx=10)

tree = ttk.Treeview(app)
tree.pack(expand=True, fill="both")

scrollbar = ttk.Scrollbar(app, orient="vertical", command=tree.yview)
scrollbar.pack(side='right', fill='y')
tree.configure(yscrollcommand=scrollbar.set)

app.mainloop()
